package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @SequenceGenerator(name = "generator", sequenceName = "orders_sequence")
    private Long id;

    @Column(name = "total_cost")
    private Double totalCost;

    @Column(name = "order_number")
    private Long orderNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",referencedColumnName = "ID")
    private UserEntity user;

    @Column(name = "address")
    private String address;

    @Column(name = "order_date")
    private Date orderDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
