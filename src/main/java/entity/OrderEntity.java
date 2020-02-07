package entity;


import javax.persistence.*;

@Entity
@Table(name = "t_order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @SequenceGenerator(name = "generator", sequenceName = "cart_sequence")
    private Long id;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "price")
    private Double price;

    @Column(name ="user_id")
    private Long userId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
