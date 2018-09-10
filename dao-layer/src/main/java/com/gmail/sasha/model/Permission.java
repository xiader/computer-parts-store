package com.gmail.sasha.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_permission")
public class Permission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(30)")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "t_role_permission",
            joinColumns = @JoinColumn(name = "f_permission_id", foreignKey=@ForeignKey(name="foreign_key_permission_id")),
            inverseJoinColumns = @JoinColumn(name = "f_role_id", foreignKey=@ForeignKey(name="foreign_key_role_id"))
    )
    private Set<Role> roles = new HashSet<>();

    public Permission() {
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void removeAllRoles(){
        roles.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Permission that = (Permission) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roles=" + roles +
                '}';
    }
}
