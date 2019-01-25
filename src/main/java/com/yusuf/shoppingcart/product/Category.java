package com.yusuf.shoppingcart.product;

public class Category {

    private String title;

    private Category category;

    public Category() {
    }

    public Category(String title) {
        if(title == null || title.isEmpty())
            throw new RuntimeException("Category cannot take in an empty String or null value for the \"title\" constructor");
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
