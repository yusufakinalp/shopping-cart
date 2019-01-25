package com.yusuf.shoppingcart.product;

public class ProductDTO {

    private String title;

    private int quantity;

    private String category;

    private double price;

    public ProductDTO(String title, int quantity, String category, double price) {
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}
