package entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "t_my_cart")
public class MyCartEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name = "product_count")
    private int productCount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private UserEntity user;

    @Column(name = "total_price")
    private Double totalPrice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id",referencedColumnName = "id")
    private ProductsEntity product;

    @Column(name = "status_cart")
    private int statusCart;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public ProductsEntity getProduct() {
        return product;
    }

    public void setProduct(ProductsEntity product) {
        this.product = product;
    }

    public int getStatusCart() {
        return statusCart;
    }

    public void setStatusCart(int statusCart) {
        this.statusCart = statusCart;
    }
}
