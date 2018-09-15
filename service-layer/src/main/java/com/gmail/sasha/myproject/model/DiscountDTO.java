package com.gmail.sasha.myproject.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class DiscountDTO {
    private Long id;

    private String name;

    private BigDecimal interestRate;

    private LocalDateTime expirationDate;


    private Set<ItemDTO> items = new HashSet<>();

    public DiscountDTO() {
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

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Set<ItemDTO> getItems() {
        return items;
    }

    public void setItems(Set<ItemDTO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "DiscountDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", interestRate=" + interestRate +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
