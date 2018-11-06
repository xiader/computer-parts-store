package com.gmail.sasha.myproject.dao.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "t_business_card")
public class BusinessCard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "working_phone")
    private String workingPhone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="f_user_id", foreignKey=@ForeignKey(name="f_user_id_business_card"))
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getWorkingPhone() {
        return workingPhone;
    }

    public void setWorkingPhone(String workingPhone) {
        this.workingPhone = workingPhone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessCard that = (BusinessCard) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(fullName, that.fullName) &&
                Objects.equals(workingPhone, that.workingPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, fullName, workingPhone);
    }

    @Override
    public String toString() {
        return "BusinessCard{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", fullName='" + fullName + '\'' +
                ", workingPhone='" + workingPhone + '\'' +
                '}';
    }
}
