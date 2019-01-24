package com.yusuf.shoppingcart.cart;

import com.yusuf.shoppingcart.delivery.DeliveryCostCalculator;
import com.yusuf.shoppingcart.delivery.DeliveryCostCalculatorFactory;
import com.yusuf.shoppingcart.delivery.IDeliveryCostCalculator;
import com.yusuf.shoppingcart.discount.CampaignStrategy;
import com.yusuf.shoppingcart.discount.Coupon;
import com.yusuf.shoppingcart.discount.Campaign;
import com.yusuf.shoppingcart.discount.CouponStrategy;
import com.yusuf.shoppingcart.product.Category;
import com.yusuf.shoppingcart.product.Product;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Product, Integer> products;

    private int productsCount;

    private double totalAmount;

    private double totalAmountAfterDiscount;

    private double couponDiscount;

    private double campaignDiscount;

    private double deliveryCost;

    public Cart() {
        products = new HashMap<>();
        productsCount = 0;
    }

    public void addItem(Product product, int quantity) {
        productsCount += quantity;
        if (products.containsKey(product)) {
            quantity += products.get(product);
            products.put(product, quantity);
        } else {
            products.put(product, quantity);
        }
        calculateTotalAmount();
    }

    public void calculateTotalAmount() {
        double total = 0;

        for (Map.Entry<Product, Integer> product :
                products.entrySet()) {
            total += product.getKey().getPrice() * product.getValue();
        }

        totalAmount = total;
    }

    public void applyDiscounts(CampaignStrategy... campaigns) {
        campaignDiscount = new CartDiscountCalculator().calculateCampaignDiscounts(this, campaigns);
    }

    public void applyCoupon(CouponStrategy... coupons) {
        couponDiscount = new CartDiscountCalculator().calculateCouponDiscount(this, coupons);
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public int getProductsCount() {
        return productsCount;
    }

    public double getCategoryProductsTotal(Category category) {
        double total = 0;
        for (Map.Entry<Product, Integer> item : products.entrySet()) {
            if (category.equals(item.getKey().getCategory()))
                total += item.getKey().getPrice() * item.getValue();
        }
        return total;
    }

    public int getCategoryProductsCount(Category category) {
        int count = 0;
        for (Map.Entry<Product, Integer> item : products.entrySet()) {
            if (category.equals(item.getKey().getCategory()))
                count = item.getValue();
        }
        return count;
    }

    public double getTotalAmount() {
        calculateTotalAmount();
        return totalAmount;
    }

    public double getTotalAmountAfterDiscount() {
        return totalAmount - campaignDiscount - couponDiscount;
    }

    public double getCouponDiscount() {
        return couponDiscount;
    }

    public double getCampaignDiscount() {
        return campaignDiscount;
    }

    public double getDeliveryCost() {
        IDeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculatorFactory().getDeliveryCostCalculator(2.0,2.0,2.45);
        return deliveryCostCalculator.calculateFor(this);
    }
}
