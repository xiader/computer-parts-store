package com.gmail.sasha.myproject.service.impl;

import com.gmail.sasha.myproject.converter.DTOConverter;
import com.gmail.sasha.myproject.converter.EntityConverter;
import com.gmail.sasha.myproject.dao.ItemDao;
import com.gmail.sasha.myproject.dao.impl.ItemDaoImpl;
import com.gmail.sasha.myproject.model.Item;
import com.gmail.sasha.myproject.converter.impl.toDto.ItemDTOConverter;
import com.gmail.sasha.myproject.converter.impl.toEntity.ItemConverter;
import com.gmail.sasha.myproject.model.ItemDTO;
import com.gmail.sasha.myproject.service.ItemService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl implements ItemService {
    private ItemDao itemDao = new ItemDaoImpl(Item.class);
    private EntityConverter<ItemDTO, Item> itemConverter = new ItemConverter();
    private DTOConverter<ItemDTO, Item> itemDTOConverter = new ItemDTOConverter();


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

    @Override
    public ItemDTO findById(ItemDTO itemDTO) {
        Session session = itemDao.getCurrentSession();
        Item item = null;
        try {
            Transaction tx = session.getTransaction();
            if (!tx.isActive()) {
                session.beginTransaction();

            }
            item = itemDao.findOne(itemDTO.getId());
            tx.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
        if(item != null) {
            return itemDTOConverter.toDTO(item);
        }
      return null;
    }

    @Override
    public void update(List<ItemDTO> itemsDTO) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction tx = session.getTransaction();
            if (!tx.isActive()) {
                session.beginTransaction();
            }
          //  List<Item> savedItems = new ArrayList<>();
            for (ItemDTO itemDTO : itemsDTO) {
                Item item = itemConverter.toEntity(itemDTO);
                itemDao.update(item);
           //     savedItems.add(item);
            }
           // List<ItemDTO> listItemDTo = itemDTOConverter.toDTOList(savedItems);
            tx.commit();


        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
    }

}


