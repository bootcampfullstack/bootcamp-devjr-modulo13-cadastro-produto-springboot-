package com.abutua.productbackend.dao;

public class ProductDAO {
    private long id;
    private String name;
    private String description;
    private boolean promotion;
    private boolean newProduct;
    private Double price;
    private CategoryDAO category;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isPromotion() {
        return promotion;
    }
    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }
    public boolean isNewProduct() {
        return newProduct;
    }
    public void setNewProduct(boolean newProduct) {
        this.newProduct = newProduct;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public CategoryDAO getCategory() {
        return category;
    }
    public void setCategory(CategoryDAO category) {
        this.category = category;
    }

    

}
