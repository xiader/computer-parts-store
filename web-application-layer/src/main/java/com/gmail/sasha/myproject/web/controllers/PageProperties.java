package com.gmail.sasha.myproject.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PageProperties {
    @Autowired
    private Environment environment;

    private String loginPagePath;
    private String usersPagePath;
    private String errorsPagePath;
    private String itemsPagePath;
    private String registrationPagePath;
    private String updateUserPagePath;
    private String newsPage;
    private String singleNewsPagePath;
    private String ordersPagePath;
    private String createItemPagePath;
    private String rolesPagePath;
    private String manageItemsPagePath;
    private String createOrderPagePath;
    private String currentUserPagePath;
    private String passwordPagePath;


    @PostConstruct
    public void initialize() {
        this.loginPagePath = environment.getProperty("login.page.path");
        this.usersPagePath = environment.getProperty("users.page.path");
        this.errorsPagePath = environment.getProperty("errors.page.path");
        this.itemsPagePath = environment.getProperty("items.page.path");
        this.registrationPagePath = environment.getProperty("registration.page.path");
        this.updateUserPagePath = environment.getProperty("update.user.page.path");
        this.newsPage = environment.getProperty("news.page.path");
        this.singleNewsPagePath = environment.getProperty("single.news.page.path");
        this.ordersPagePath = environment.getProperty("orders.page.path");
        this.createItemPagePath = environment.getProperty("create.item.page.path");
        this.rolesPagePath = environment.getProperty("roles.page.path");
        this.manageItemsPagePath = environment.getProperty("manage.items.page.path");
        this.createOrderPagePath = environment.getProperty("create.order.page.path");
        this.currentUserPagePath = environment.getProperty("current.user.page.path");
        this.passwordPagePath = environment.getProperty("password.page.path");
    }

    public Environment getEnvironment() {
        return environment;
    }

    public String getLoginPagePath() {
        return loginPagePath;
    }

    public void setLoginPagePath(String loginPagePath) {
        this.loginPagePath = loginPagePath;
    }

    public String getUsersPagePath() {
        return usersPagePath;
    }

    public void setUsersPagePath(String usersPagePath) {
        this.usersPagePath = usersPagePath;
    }

    public String getErrorsPagePath() {
        return errorsPagePath;
    }

    public void setErrorsPagePath(String errorsPagePath) {
        this.errorsPagePath = errorsPagePath;
    }

    public String getItemsPagePath() {
        return itemsPagePath;
    }

    public void setItemsPagePath(String itemsPagePath) {
        this.itemsPagePath = itemsPagePath;
    }

    public String getRegistrationPagePath() {
        return registrationPagePath;
    }

    public void setRegistrationPagePath(String registrationPagePath) {
        this.registrationPagePath = registrationPagePath;
    }

    public String getUpdateUserPagePath() {
        return updateUserPagePath;
    }

    public void setUpdateUserPagePath(String updateUserPagePath) {
        this.updateUserPagePath = updateUserPagePath;
    }

    public String getNewsPage() {
        return newsPage;
    }

    public void setNewsPage(String newsPage) {
        this.newsPage = newsPage;
    }

    public String getSingleNewsPagePath() {
        return singleNewsPagePath;
    }

    public void setSingleNewsPagePath(String singleNewsPagePath) {
        this.singleNewsPagePath = singleNewsPagePath;
    }

    public String getOrdersPagePath() {
        return ordersPagePath;
    }

    public void setOrdersPagePath(String ordersPagePath) {
        this.ordersPagePath = ordersPagePath;
    }

    public String getCreateItemPagePath() {
        return createItemPagePath;
    }

    public void setCreateItemPagePath(String createItemPagePath) {
        this.createItemPagePath = createItemPagePath;
    }

    public String getRolesPagePath() {
        return rolesPagePath;
    }

    public void setRolesPagePath(String rolesPagePath) {
        this.rolesPagePath = rolesPagePath;
    }

    public String getManageItemsPagePath() {
        return manageItemsPagePath;
    }

    public void setManageItemsPagePath(String manageItemsPagePath) {
        this.manageItemsPagePath = manageItemsPagePath;
    }

    public String getCreateOrderPagePath() {
        return createOrderPagePath;
    }

    public void setCreateOrderPagePath(String createOrderPagePath) {
        this.createOrderPagePath = createOrderPagePath;
    }

    public String getCurrentUserPagePath() {
        return currentUserPagePath;
    }

    public void setCurrentUserPagePath(String currentUserPagePath) {
        this.currentUserPagePath = currentUserPagePath;
    }

    public String getPasswordPagePath() {
        return passwordPagePath;
    }

    public void setPasswordPagePath(String passwordPagePath) {
        this.passwordPagePath = passwordPagePath;
    }
}
