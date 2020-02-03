package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/*@Getter
@Setter*/
@Entity
@Table(name = "t_users")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "admin_status")
    private int adminStatus;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "address")
    private String address;

    @OneToOne(mappedBy =  "user" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private MyCartEntity myCartEntity;

    public UserEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(int adminStatus) {
        this.adminStatus = adminStatus;
    }

}
