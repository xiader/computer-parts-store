package com.gmail.sasha.myproject.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Service("pageProperties")
public class PageProperties {

    @Autowired
    private Environment environment;

    private Long elementsOnPage;
    private String loginPagePath;
    private String usersPagePath;

    @Value("${error.try.again}")
    private String errorsPagePath;
    private String itemsPagePath;
    private String updateUserPagePath;
    private String businessCardPagePath;
    private String businessCardCreatePagePath;

    @Value("${registration.page}")
    private String registrationPagePath;

    @Value("${user.password.page}")
    private String userPasswordPagePath;

    @Value("${profile.page}")
    private String profilePagePath;


    @PostConstruct
    public void initialize() {
        this.elementsOnPage = Long.valueOf(Objects.requireNonNull(environment.getProperty("default.elements.on.page")));
        this.loginPagePath = environment.getProperty("login.page");
        this.usersPagePath = environment.getProperty("users.page");
        this.itemsPagePath = environment.getProperty("items.page");
        this.updateUserPagePath = environment.getProperty("update.user.page");
        this.businessCardPagePath = environment.getProperty("business.card.page");
        this.businessCardCreatePagePath = environment.getProperty("business.card.create.page");
    }

    public Long getElementsOnPage() {
        return elementsOnPage;
    }

    public String getLoginPagePath() {
        return loginPagePath;
    }

    public String getUsersPagePath() {
        return usersPagePath;
    }

    public String getErrorsPagePath() {
        return errorsPagePath;
    }

    public String getItemsPagePath() {
        return itemsPagePath;
    }

    public String getRegistrationPagePath() {
        return registrationPagePath;
    }

    public String getUpdateUserPagePath() {
        return updateUserPagePath;
    }

    public String getBusinessCardPagePath() {
        return businessCardPagePath;
    }

    public String getBusinessCardCreatePagePath() {
        return businessCardCreatePagePath;
    }

    public String getUserPasswordPagePath() {
        return userPasswordPagePath;
    }

    public String getProfilePagePath() {
        return profilePagePath;
    }
}
