package com.gmail.sasha.dao.services.model;

import java.time.LocalDateTime;

public class CommentDTO {

    private Long id;

    private String content;

    private LocalDateTime created;

    private UserDTO user;

    private NewsDTO news;

    public CommentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public NewsDTO getNews() {
        return news;
    }

    public void setNews(NewsDTO news) {
        this.news = news;
    }
}
