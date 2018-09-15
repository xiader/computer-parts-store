package com.gmail.sasha.myproject.service.impl;

import com.gmail.sasha.myproject.converter.DTOConverter;
import com.gmail.sasha.myproject.converter.impl.toDto.ItemDTOConverter;
import com.gmail.sasha.myproject.dao.DiscountDao;
import com.gmail.sasha.myproject.dao.ItemDao;
import com.gmail.sasha.myproject.dao.impl.DiscountDaoImpl;
import com.gmail.sasha.myproject.dao.impl.ItemDaoImpl;
import com.gmail.sasha.myproject.model.*;
import com.gmail.sasha.myproject.service.ItemDiscountService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemDiscountServiceImpl implements ItemDiscountService {
    private ItemDao itemDao = new ItemDaoImpl(Item.class);
    private DiscountDao discountDao = new DiscountDaoImpl(Discount.class);
    private DTOConverter<ItemDTO, Item> itemDTOConverter = new ItemDTOConverter();

    @Override
    public void assignToRangeOfItemsCorrespondingDiscounts(int minItemPrice, int maxItemPrice, BigDecimal discountValue) {

        Session session = itemDao.getCurrentSession();
        try {
            Transaction tx = session.getTransaction();
            if (!tx.isActive()) {
                session.beginTransaction();
            }
            List<Item> items = itemDao.findItemsInPriceRange(minItemPrice, maxItemPrice);
            List<Discount> discounts = discountDao.findDiscountByinterestRate(discountValue);
            Set<Discount> discountSet = new HashSet<>(discounts);
            for (Item ie : items) {
                ie.setDiscounts(discountSet);
            }
            Set<Item> itemsSet = new HashSet<>(items);
            for (Discount d : discounts) {
                d.setItems(itemsSet);
            }


            tx.commit();


        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public List<ItemWithDiscountedPrice> showItemsWithDiscountedPrice() {
        Session session = itemDao.getCurrentSession();

        try {
            Transaction tx = session.getTransaction();
            if (!tx.isActive()) {
                session.beginTransaction();
            }
            List<Item> items = itemDao.findAllOrderByDiscount();

            List<ItemDTO> itemsdto = itemDTOConverter.toDTOList(items);

            tx.commit();

            List<ItemWithDiscountedPrice> itemWithDiscountedPrices = new ArrayList<>();
            for (ItemDTO idtoe : itemsdto) {
                ItemWithDiscountedPrice iwdp = new ItemWithDiscountedPrice();
                iwdp.setId(idtoe.getId());
                iwdp.setDescription(idtoe.getDescription());
                iwdp.setName(iwdp.getName());
                iwdp.setPrice(idtoe.getPrice());
                iwdp.setName(idtoe.getName());
                iwdp.setUniqueNumber(idtoe.getUniqueNumber());
                Set<DiscountDTO> discountsForCurrentItem = idtoe.getDiscounts();

                if (discountsForCurrentItem != null) {
                    //count all discounts
                    BigDecimal totalDiscountIn = new BigDecimal(0);
                    //finalprice with all discounts
                    BigDecimal price = idtoe.getPrice();
                    BigDecimal interstRatefull = new BigDecimal(0);
                    for (DiscountDTO discountDTO : discountsForCurrentItem) {
                        interstRatefull = interstRatefull.add(discountDTO.getInterestRate());
                    }
                        BigDecimal temp =  price.multiply(interstRatefull);
                        temp = temp.divide(new BigDecimal(100));
                        price = (price.subtract(temp));
                        totalDiscountIn = totalDiscountIn.add(interstRatefull);

                    iwdp.setPriceWithDiscount(price);
                    iwdp.setTotalDiscountPercent(totalDiscountIn);
                }
                itemWithDiscountedPrices.add(iwdp);
            }
            return itemWithDiscountedPrices;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
        return new ArrayList<>();
    }
}
