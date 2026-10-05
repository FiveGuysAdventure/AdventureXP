package com.adventurexp.model;


import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name", nullable = false, unique = true, length = 60)
    private String productName;

    @Column(name = "price", nullable = false)
    private int price;

    public Product() {

    }

    public Product(String productName, int price) {
        this.productName = productName;
        this.price = price;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
