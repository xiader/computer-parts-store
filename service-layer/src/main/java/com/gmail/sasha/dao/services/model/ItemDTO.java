package com.gmail.sasha.dao.services.model;

import com.gmail.sasha.model.Order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class ItemDTO implements Serializable {

    private Long id;

    private String name;

    private String description;

    private String uniqueNumber;

    private BigDecimal price;

    private List<Order> orderDTOS = new ArrayList<>();


    public ItemDTO() {
    }

    public ItemDTO(Long id, String name, String description, String uniqueNumber, BigDecimal price, List<Order> orderDTOS) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.uniqueNumber = uniqueNumber;
        this.price = price;
        this.orderDTOS = orderDTOS;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(String uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Order> getOrderDTOS() {
        return orderDTOS;
    }

    public void setOrderDTOS(List<Order> orderDTOS) {
        this.orderDTOS = orderDTOS;
    }


    @Override
    public String toString() {
        return "ItemDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", uniqueNumber='" + uniqueNumber + '\'' +
                ", price=" + price +
                '}';
    }
}
