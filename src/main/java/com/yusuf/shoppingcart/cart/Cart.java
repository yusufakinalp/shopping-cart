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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cart {

    private Map<Product, Integer> products;

    private Set<Category> categories;

    private int productsCount;

    private double totalAmount;

    private double totalAmountAfterDiscount;

    private double couponDiscount;

    private double campaignDiscount;

    private double deliveryCost;

    public Cart() {
        products = new HashMap<>();
        categories = new HashSet<>();
        productsCount = 0;
        couponDiscount = 0;
        campaignDiscount = 0;
    }

    public void addItem(Product product, int quantity) {
        productsCount += quantity;
        categories.add(product.getCategory());
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
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public void print() {
        String divider = "--------------------------------------------------";

        for (Category category: categories) {
            System.out.println(category.getTitle());
            System.out.println(divider);
            for (Map.Entry<Product, Integer> item : products.entrySet()) {
                if (category.equals(item.getKey().getCategory())) {
                    System.out.printf("%-20s %15d %10.2f %n",item.getKey().getTitle(), item.getValue(), item.getKey().getPrice());
                }
            }
            System.out.println(divider);
        }
        System.out.printf("Total Price                        : %10.2f %n", getTotalAmount());
        System.out.printf("Campaign Discount                  : %10.2f %n", getCampaignDiscount());
        System.out.printf("Coupon Discount                    : %10.2f %n", getCouponDiscount());
        System.out.printf("Total Amount After Discount        : %10.2f %n", getTotalAmountAfterDiscount());
        System.out.printf("Delivery Cost                      : %10.2f %n", getDeliveryCost());
    }
}
