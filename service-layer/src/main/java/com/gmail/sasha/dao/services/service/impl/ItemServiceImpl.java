package com.gmail.sasha.dao.services.service.impl;

import com.gmail.sasha.dao.ItemDao;
import com.gmail.sasha.dao.impl.ItemDaoImpl;
import com.gmail.sasha.dao.services.converter.impl.toDto.ItemDTOConverter;
import com.gmail.sasha.dao.services.converter.impl.toEntity.ItemConverter;
import com.gmail.sasha.dao.services.model.ItemDTO;
import com.gmail.sasha.dao.services.service.ItemService;
import com.gmail.sasha.model.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl implements ItemService {
    private ItemDao itemDao = new ItemDaoImpl(Item.class);
    private ItemConverter itemConverter = new ItemConverter();
    private ItemDTOConverter itemDTOConverter = new ItemDTOConverter();


    @Override
    public List<ItemDTO> save(List<ItemDTO> itemList) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction tx = session.getTransaction();
            if (!tx.isActive()) {
                session.beginTransaction();
            }
            List<Item> savedItems = new ArrayList<>();
            for (ItemDTO itemDTO : itemList) {
                Item item = itemConverter.toEntity(itemDTO);
                itemDao.create(item);
                savedItems.add(item);
            }
            List<ItemDTO> listItemDTo = itemDTOConverter.toDTOList(savedItems);
            tx.commit();

            return listItemDTo;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return null;
    }

    @Override
    public List<ItemDTO> getItemsInPriceRange(int from, int to) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction tx = session.getTransaction();
            if (!tx.isActive()) {
                session.beginTransaction();
            }
            List<Item> savedItems = itemDao.findItemsInPriceRange(from, to);

            List<ItemDTO> listItemDTo = itemDTOConverter.toDTOList(savedItems);
            tx.commit();

            return listItemDTo;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
        return new ArrayList<>();
    }
}


