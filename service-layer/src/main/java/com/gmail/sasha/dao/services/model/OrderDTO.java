package com.gmail.sasha.dao.services.model;

import java.time.LocalDateTime;

public class OrderDTO {


    private UserItemIdDTO id;

    private LocalDateTime created;

    private int quantity;

    private ItemDTO item;

    private UserDTO user;

    public OrderDTO() {
    }

    public UserItemIdDTO getId() {
        return id;
    }

    public void setId(UserItemIdDTO id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
