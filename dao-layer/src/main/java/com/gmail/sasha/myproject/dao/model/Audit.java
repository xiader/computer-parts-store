package com.gmail.sasha.myproject.dao.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@SuppressWarnings("NullableProblems")
@Entity
@Table(name = "t_audit")
public class Audit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audit_id")
    private Long id;

    @Column(name = "event_type", columnDefinition = "VARCHAR(50)")
    private String eventType;

    @Column(name = "date_created")
    private LocalDateTime created;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "f_user_id",  foreignKey=@ForeignKey(name="f_user_id_audit"), nullable = false)
    @NotNull
    private User user;

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
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

        Audit audit = (Audit) o;

        if (id != null ? !id.equals(audit.id) : audit.id != null) return false;
        if (eventType != null ? !eventType.equals(audit.eventType) : audit.eventType != null)
            return false;
        return created != null ? created.equals(audit.created) : audit.created == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (eventType != null ? eventType.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Audit{" +
                "id=" + id +
                ", eventType='" + eventType + '\'' +
                ", created=" + created +
                ", user=" + user +
                '}';
    }
}
