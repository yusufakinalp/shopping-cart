package com.yusuf.shoppingcart.cart;

public class CartDTO {

    private double totalAmount;

    private double campaignDiscount;

    private double couponDiscount;

    private double totalAmountAfterDiscount;

    private double deliveryCost;

    public CartDTO(double totalAmount, double campaignDiscount, double couponDiscount, double totalAmountAfterDiscount, double deliveryCost) {
        this.totalAmount = totalAmount;
        this.campaignDiscount = campaignDiscount;
        this.couponDiscount = couponDiscount;
        this.totalAmountAfterDiscount = totalAmountAfterDiscount;
        this.deliveryCost = deliveryCost;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getCampaignDiscount() {
        return campaignDiscount;
    }

    public double getCouponDiscount() {
        return couponDiscount;
    }

    public double getTotalAmountAfterDiscount() {
        return totalAmountAfterDiscount;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }
}
