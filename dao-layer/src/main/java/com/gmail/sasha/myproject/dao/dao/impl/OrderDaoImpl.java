package com.gmail.sasha.myproject.dao.dao.impl;

import com.gmail.sasha.myproject.dao.dao.GenericDaoImpl;
import com.gmail.sasha.myproject.dao.dao.OrderDao;
import com.gmail.sasha.myproject.dao.model.Order;
import com.gmail.sasha.myproject.dao.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings({"JpaQlInspection", "unchecked"})
@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    @Autowired
    private Pagination pagination;

    public OrderDaoImpl() {
        super(Order.class);
    }

    @Override
    public List<Order> getOrdersWithUserItemsAndPrice(int page, int elementsOnPage) {
        return
                getCurrentSession()
                        .createQuery("select o from Order as o")
                        .setFirstResult(pagination.calculateOffset(page, elementsOnPage))
                        .setMaxResults(elementsOnPage)
                        .list();
    }

    @Override
    public List<Order> findOrdersByUserId(Long userId, int page, int elementsOnPage) {
        return getCurrentSession()
                .createQuery("from Order as o where o.user.id = :userId")
                .setParameter("userId", userId)
                .setFirstResult(pagination.calculateOffset(page, elementsOnPage))
                .setMaxResults(elementsOnPage)
                .list();
    }

}
