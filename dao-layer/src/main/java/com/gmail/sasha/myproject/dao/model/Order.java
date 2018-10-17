package com.gmail.sasha.myproject.dao.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@SuppressWarnings("NullableProblems")
@Entity
@Table(name = "t_order")
public class Order implements Serializable {

    @EmbeddedId
    private UserItemId id;

    @Column(name = "date_created")
    private LocalDateTime created;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status = OrderStatusEnum.NEW;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("itemId")
    @NotNull
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @NotNull
    private User user;


    public Order() {
    }

    public UserItemId getId() {
        return id;
    }

    public void setId(UserItemId id) {
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (quantity != order.quantity) return false;
        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        return created != null ? created.equals(order.created) : order.created == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", created=" + created +
                ", quantity=" + quantity +
                ", item=" + item +
                ", user=" + user +
                '}';
    }
}
