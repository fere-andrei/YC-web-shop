package entity;

import javax.persistence.*;

@Entity
@Table(name = "t_products")
public class ProductsEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "stock_number")
    private int stockNumber;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private Double price;

    @OneToOne(mappedBy =  "product" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private MyCartEntity myCartEntity;

    public ProductsEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(int stockNumber) {
        this.stockNumber = stockNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public MyCartEntity getMyCartEntity() {
        return myCartEntity;
    }

    public void setMyCartEntity(MyCartEntity myCartEntity) {
        this.myCartEntity = myCartEntity;
    }
}
