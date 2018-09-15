package com.gmail.sasha.myproject.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "t_discount")
public class Discount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "interest_rate", precision = 10, scale = 2)
    private BigDecimal interestRate;

    @Column(name = "date_expiration")
    private LocalDateTime expirationDate;

    @ManyToMany
    @JoinTable(
            name = "t_item_discount",
            joinColumns = @JoinColumn(name = "f_discount_id", foreignKey=@ForeignKey(name="foreign_key_discount_id")),
            inverseJoinColumns = @JoinColumn(name = "f_item_id", foreignKey=@ForeignKey(name="foreign_key_item_id"))
    )
    private Set<Item> items = new HashSet<>();


    public Discount() {
    }

    public Discount(String name, BigDecimal interestRate, LocalDateTime expirationDate, Set<Item> items) {
        this.name = name;
        this.interestRate = interestRate;
        this.expirationDate = expirationDate;
        this.items = items;

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

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return Objects.equals(id, discount.id) &&
                Objects.equals(name, discount.name) &&
                Objects.equals(interestRate, discount.interestRate) &&
                Objects.equals(expirationDate, discount.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, interestRate, expirationDate);
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", interestRate=" + interestRate +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
