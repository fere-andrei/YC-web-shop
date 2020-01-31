package dto;


import entity.ProductsEntity;
import entity.UserEntity;

import javax.persistence.*;

public class MyCartDTO {


    private int id;

    private int productCount;

    private UserEntity user;

    private Double totalPrice;

    private ProductsEntity product;

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
