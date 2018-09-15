package com.gmail.sasha.myproject.model;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private Long id;

    private String email;

    private String surname;

    private String name;

    private String password;

    private RoleDTO role;

    private List<OrderDTO> orders = new ArrayList<>();

    public UserDTO() {
    }

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.surname = entity.getSurname();
        this.name = entity.getName();
        this.password = entity.getPassword();
    }

    public UserDTO(Long id, String email, String surname, String name, String password) {
        this.id = id;
        this.email = email;
        this.surname = surname;
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        if (id != null ? !id.equals(userDTO.id) : userDTO.id != null)
            return false;
        if (email != null ? !email.equals(userDTO.email) : userDTO.email != null)
            return false;
        if (surname != null ? !surname.equals(userDTO.surname) : userDTO.surname != null)
            return false;
        if (name != null ? !name.equals(userDTO.name) : userDTO.name != null)
            return false;
        return password != null ? password.equals(userDTO.password) : userDTO.password == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}