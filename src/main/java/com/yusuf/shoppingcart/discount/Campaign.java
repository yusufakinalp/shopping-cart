package com.yusuf.shoppingcart.discount;

import com.yusuf.shoppingcart.cart.Cart;
import com.yusuf.shoppingcart.product.Category;

public class Campaign implements CampaignStrategy {

    private Category category;

    private double ratio;

    private int minItems;

    private DiscountType discountType;

    public Campaign() {
    }

    public Campaign(Category category, double ratio, int minItems, DiscountType discountType) {
        if (ratio < 0)
            throw new RuntimeException("Campaign cannot take in an negative value for the \"ratio\" constructor");
        if (minItems < 0)
            throw new RuntimeException("Campaign items cannot take in an negative value for the \"minItems\" constructor");
        this.category = category;
        this.ratio = ratio;
        this.minItems = minItems;
        this.discountType = discountType;
    }

    @Override
    public double applyDiscount(Cart cart) {
        double totalAmount = cart.getCategoryProductsTotal(category);
        int productsCount =cart.getCategoryProductsCount(category);
        double discount = 0;

        if (productsCount >= minItems) {
            if (discountType.equals(DiscountType.RATE)) {
                discount = totalAmount * ratio / 100;
            } else if (discountType.equals(DiscountType.AMOUNT)) {
                int times = productsCount / minItems;
                discount = ratio * times;
            }
        }
        return discount;
    }

    public Category getCategory() {
        return category;
    }

    public double getRatio() {
        return ratio;
    }

    public int getMinItems() {
        return minItems;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }
}
