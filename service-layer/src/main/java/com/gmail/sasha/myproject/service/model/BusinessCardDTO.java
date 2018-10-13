package com.gmail.sasha.myproject.service.model;

public class BusinessCardDTO {

    private Long id;

    private String title;

    private String fullName;

    private String workingPhone;

    private UserDTO user;

    public BusinessCardDTO() {
    }

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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BusinessCardDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", fullName='" + fullName + '\'' +
                ", workingPhone='" + workingPhone + '\'' +
                ", user=" + user +
                '}';
    }
}
