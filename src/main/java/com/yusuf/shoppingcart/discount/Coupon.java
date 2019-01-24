package com.yusuf.shoppingcart.discount;

import com.yusuf.shoppingcart.cart.Cart;

public class Coupon implements CouponStrategy {

    private double minTotalAmount;

    private double discountAmount;

    private DiscountType discountType;

    public Coupon() {
    }

    public Coupon(double minTotalAmount, double discountAmount, DiscountType discountType) {
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
}
