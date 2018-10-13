package com.gmail.sasha.myproject.dao;

import com.gmail.sasha.myproject.config.AppConfig;
import com.gmail.sasha.myproject.dao.config.DatabaseConfig;
import com.gmail.sasha.myproject.dao.dao.BusinessCardDao;
import com.gmail.sasha.myproject.dao.dao.UserDao;
import com.gmail.sasha.myproject.dao.model.BusinessCard;
import com.gmail.sasha.myproject.dao.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { DatabaseConfig.class, AppConfig.class}, loader = AnnotationConfigContextLoader.class)
public class BusinessCardTest {


    @Autowired
    private UserDao userdao;

    @Autowired
    private BusinessCardDao businessCardDao;

    @Test
    @Rollback(false)
    @Transactional//(propagation=Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
     public void testSave() throws InterruptedException {
        BusinessCard bc = new BusinessCard();
        bc.setFullName("Sidorov Arnold Rembovich");
        bc.setTitle("titul of business card");
        bc.setWorkingPhone("2020327");

        User useradmin = userdao.findById(1L);
         userdao.getCurrentSession().evict(useradmin);
        assertNotNull(useradmin);
        Thread.sleep(2000);
        System.out.println(useradmin);
        bc.setUser(useradmin);
        System.out.println(bc);
       /* List<BusinessCard> businessCards = new ArrayList<>();
        businessCards.add(bc);*/
        businessCardDao.create(bc);
        Thread.sleep(2000);
      //  businessCardDao.getCurrentSession().getTransaction().commit();
        //businessCardDao.getCurrentSession().flush();
//useradmin.setBusinessCards(businessCards);
    }

    @Test
    void getByWorkingPhone(){

    }
}
