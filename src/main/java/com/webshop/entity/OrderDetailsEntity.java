package com.webshop.entity;


import javax.persistence.*;

@Entity
@Table(name = "t_order_details")
public class OrderDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @SequenceGenerator(name = "generator", sequenceName = "order_sequence")
    private Long id;

    @Column(name = "quantity")
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",referencedColumnName = "ID")
    private UserEntity user;

    @Column(name = "price")
    private Double price;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "order_number")
    private Long orderNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }
}
