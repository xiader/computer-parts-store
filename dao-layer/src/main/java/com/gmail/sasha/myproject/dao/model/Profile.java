package com.gmail.sasha.myproject.dao.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_profile")
public class Profile implements Serializable {


    @GenericGenerator(name = "generator",
            strategy = "foreign", parameters = @Parameter(
            name = "property", value = "user"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "profile_id")
    private Long profileId;

    @Column(name = "address", columnDefinition = "VARCHAR(150)")
    private String address;

    @Column(name = "telephone", columnDefinition = "VARCHAR(20)")
    private String telephone;

    @PrimaryKeyJoinColumn(name = "profile_id", foreignKey = @ForeignKey(name="f_profile_id_user"))
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private User user;

    public Profile() {
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

        Profile profile = (Profile) o;

        if (profileId != null ? !profileId.equals(profile.profileId) : profile.profileId != null)
            return false;
        if (address != null ? !address.equals(profile.address) : profile.address != null)
            return false;
        return telephone != null ? telephone.equals(profile.telephone) : profile.telephone == null;
    }

    @Override
    public int hashCode() {
        int result = profileId != null ? profileId.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "profileId=" + profileId +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", user=" + user +
                '}';
    }
}
