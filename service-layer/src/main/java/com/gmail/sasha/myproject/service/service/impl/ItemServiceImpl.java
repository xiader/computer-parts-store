package com.gmail.sasha.myproject.service.service.impl;

import com.gmail.sasha.myproject.dao.dao.ItemDao;
import com.gmail.sasha.myproject.dao.model.Item;
import com.gmail.sasha.myproject.service.converter.DTOConverter;
import com.gmail.sasha.myproject.service.converter.EntityConverter;
import com.gmail.sasha.myproject.service.model.ItemDTO;
import com.gmail.sasha.myproject.service.service.ItemService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDao itemDao;
    @Autowired
    @Qualifier("itemEntityConverter")
    private EntityConverter<ItemDTO, Item> itemConverter;
    @Autowired
    @Qualifier("itemDTOConverter")
    private DTOConverter<ItemDTO, Item> itemDTOConverter;


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
        if (item != null) {
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


