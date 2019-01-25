package com.yusuf.shoppingcart.discount;

import com.yusuf.shoppingcart.cart.Cart;

public class Coupon implements CouponStrategy {

    private double minTotalAmount;

    private double discountAmount;

    private DiscountType discountType;

    public Coupon() {
    }

    public Coupon(double minTotalAmount, double discountAmount, DiscountType discountType) {
        if (minTotalAmount < 0)
            throw new RuntimeException("Minimum total amount cannot take in an negative value for the \"Coupon\" constructor");
        if (discountAmount < 0)
            throw new RuntimeException("Discount amount cannot take in an negative value for the \"Coupon\" constructor");

        this.minTotalAmount = minTotalAmount;
        this.discountAmount = discountAmount;
        this.discountType = discountType;
    }

    @Override
    public double applyDiscount(Cart cart) {
        double totalAmount = cart.getTotalAmount();
        double discount = 0;

        if (totalAmount >= minTotalAmount) {
            if (discountType.equals(DiscountType.RATE)) {
                discount = totalAmount * discountAmount / 100;
            } else if (discountType.equals(DiscountType.AMOUNT)) {
                discount = discountAmount;
            }
        }

        return discount;
    }

    public double getMinTotalAmount() {
        return minTotalAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }
}
