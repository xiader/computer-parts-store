package com.gmail.sasha.myproject.service.service.impl;

import com.gmail.sasha.myproject.dao.dao.ItemDao;
import com.gmail.sasha.myproject.service.service.UploadService;
import com.gmail.sasha.myproject.service.service.impl.upload.BodyType;
import com.gmail.sasha.myproject.service.service.impl.upload.ItemType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {
    private static Logger logger = LogManager.getLogger(UploadServiceImpl.class);

    @Autowired
    private ItemDao itemDao;


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void uploadByFile() {
        List<ItemType> itemsUnmarshalled = Items.unmarshallWithSavedFile();
        List<com.gmail.sasha.myproject.dao.model.Item> items = convert(itemsUnmarshalled);
        System.out.println(items);
        for (com.gmail.sasha.myproject.dao.model.Item item : items) {
            itemDao.create(item);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void uploadByBytes(byte[] bytes) {
        List<ItemType> itemsUnmarshalled = Items.unmarshallByBytes(bytes);
        List<com.gmail.sasha.myproject.dao.model.Item> items = convert(itemsUnmarshalled);
        System.out.println(items);
        for (com.gmail.sasha.myproject.dao.model.Item item : items) {
            itemDao.create(item);
        }
    }


    private List<com.gmail.sasha.myproject.dao.model.Item> convert(List<ItemType> storeItems) {
        List<com.gmail.sasha.myproject.dao.model.Item> items = new ArrayList<>();
        for (ItemType element : storeItems) {
            com.gmail.sasha.myproject.dao.model.Item item = new com.gmail.sasha.myproject.dao.model.Item();
            item.setDescription(element.getDescription());
            item.setName(element.getName());
            item.setUniqueNumber(element.getUniqueNumber());
            item.setPrice(element.getPrice());
            item.setAvailable(element.getAvailable());
            items.add(item);
        }
        return items;
    }

    static class Items {
        static List<ItemType> unmarshallByBytes(byte[] bytes) {
            ByteArrayInputStream input = new ByteArrayInputStream(bytes);
            Unmarshaller unmarshaller;
            try {
                JAXBContext jaxb = JAXBContext.newInstance(BodyType.class);
                unmarshaller = jaxb.createUnmarshaller();
                BodyType storeItems = (BodyType) unmarshaller.unmarshal(input);
                return new ArrayList<>(storeItems.getItem());
            } catch (JAXBException e) {
                logger.error(e.getMessage(), e);
            }
            return Collections.emptyList();

        }


        static List<ItemType> unmarshallWithSavedFile() {
            File file = new File("D:/newFolder/items.xml");
            try {
                JAXBContext jaxb = JAXBContext.newInstance(BodyType.class);
                Unmarshaller unmarshaller = jaxb.createUnmarshaller();
                BodyType storeItems = (BodyType) unmarshaller.unmarshal(file);
                return new ArrayList<>(storeItems.getItem());
            } catch (JAXBException e) {
                logger.error(e.getMessage(), e);
            }
            return Collections.emptyList();
        }
    }
}
