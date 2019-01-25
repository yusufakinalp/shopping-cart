package com.yusuf.shoppingcart.product;

import com.yusuf.shoppingcart.product.Category;

public class Product {

    private String title;

    private double price;

    private Category category;

    public Product(String title, double price, Category category) {
        if(title == null || title.isEmpty())
            throw new RuntimeException("Product cannot take in an empty String or null value for the \"title\" constructor");

        if(price < 0)
            throw new RuntimeException("Product cannot take in an empty negative value for the \"price\" constructor");

        if(category == null)
            throw new RuntimeException("Product cannot take in an null value for the \"category\" constructor");

        this.title = title;
        this.price = price;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
