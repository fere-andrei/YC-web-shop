package com.webshop.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/*@Getter
@Setter*/
@Entity
@Table(name = "t_user")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "user_status")
    private int userStatus;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "address")
    private String address;

    @Column(name = "register_date")
    private Date registerDate;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roleEntities;

    @Column(name = "active")
    private Boolean active;

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int adminStatus) {
        this.userStatus = adminStatus;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Set<RoleEntity> getRoleEntities() {
        return roleEntities;
    }

    public void setRoleEntities(Set<RoleEntity> roleEntities) {
        this.roleEntities = roleEntities;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
