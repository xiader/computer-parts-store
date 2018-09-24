package com.gmail.sasha.myproject.service.service.impl;

import com.gmail.sasha.myproject.dao.dao.DiscountDao;
import com.gmail.sasha.myproject.dao.dao.ItemDao;
import com.gmail.sasha.myproject.dao.model.Discount;
import com.gmail.sasha.myproject.dao.model.Item;
import com.gmail.sasha.myproject.service.converter.DTOConverter;
import com.gmail.sasha.myproject.service.model.DiscountDTO;
import com.gmail.sasha.myproject.service.model.ItemDTO;
import com.gmail.sasha.myproject.service.model.ItemWithDiscountedPrice;
import com.gmail.sasha.myproject.service.service.ItemDiscountService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ItemDiscountServiceImpl implements ItemDiscountService {
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private DiscountDao discountDao;
    @Autowired
    @Qualifier("itemDTOConverter")
    private DTOConverter<ItemDTO, Item> itemDTOConverter;

    @Override
    public void assignToRangeOfItemsCorrespondingDiscounts(int minItemPrice, int maxItemPrice, BigDecimal discountValue) {

        Session session = itemDao.getCurrentSession();
        try {
            Transaction tx = session.getTransaction();
            if (!tx.isActive()) {
                session.beginTransaction();
            }
            List<Item> items = itemDao.findItemsInPriceRange(minItemPrice, maxItemPrice);
            List<Discount> discounts = discountDao.findDiscountByInterestRate(discountValue);
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
                    BigDecimal temp = price.multiply(interstRatefull);
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
