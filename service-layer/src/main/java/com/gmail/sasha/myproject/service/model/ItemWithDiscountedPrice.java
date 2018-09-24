package com.gmail.sasha.myproject.service.model;

import java.math.BigDecimal;

public class ItemWithDiscountedPrice {

    private Long id;

    private String name;

    private String description;

    private String uniqueNumber;

    private BigDecimal price;

    private BigDecimal priceWithDiscount;

    private BigDecimal totalDiscountPercent;

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

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }

    public BigDecimal getTotalDiscountPercent() {
        return totalDiscountPercent;
    }

    public void setTotalDiscountPercent(BigDecimal totalDiscountPercent) {
        this.totalDiscountPercent = totalDiscountPercent;
    }


    @Override
    public String toString() {
        return "ItemWithDiscountedPrice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", uniqueNumber='" + uniqueNumber + '\'' +
                ", price=" + price +
                ", priceWithDiscount=" + priceWithDiscount +
                ", totalDiscountPercent=" + totalDiscountPercent +
                '}';
    }
}
