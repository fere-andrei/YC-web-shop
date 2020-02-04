package entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "t_my_cart")
public class MyCartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @SequenceGenerator(name = "generator", sequenceName = "cart_sequence")
    private Long id;

    @Column(name = "product_count")
    private int productCount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",referencedColumnName = "ID")
    private UserEntity user;

    @Column(name = "total_price")
    private Double totalPrice;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID",referencedColumnName = "ID")
    private List<ProductsEntity> products = new ArrayList<>();

    @Column(name = "PRODUCT_ID")
    private Long productId;


    @Column(name = "status_cart")
    private int statusCart;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<ProductsEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsEntity> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getStatusCart() {
        return statusCart;
    }

    public void setStatusCart(int statusCart) {
        this.statusCart = statusCart;
    }
}
