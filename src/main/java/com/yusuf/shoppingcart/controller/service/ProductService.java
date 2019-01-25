package com.yusuf.shoppingcart.controller.service;

import com.yusuf.shoppingcart.discount.*;
import com.yusuf.shoppingcart.product.Category;
import com.yusuf.shoppingcart.product.Product;

import java.util.*;

public class ProductService {

    public static Map<String, Product> products;

    private static Category food = new Category("food");
    private static Category cloth = new Category("cloth");

    public static List<CampaignStrategy> campaignStrategies;
    public static List<CouponStrategy> couponStrategies;

    public static Map<String, Product> getProducts() {
        if (products == null) {
            products = new HashMap<>();

            Product apple = new Product("apple", 5.0, food);
            Product shirt = new Product("shirt", 25.0, cloth);
            Product tomato = new Product("tomato", 5.0, food);
            Product shoes = new Product("shoes", 25.0, cloth);

            products.put(apple.getTitle(),apple);
            products.put(shirt.getTitle(), shirt);
            products.put(tomato.getTitle(), tomato);
            products.put(shoes.getTitle(), shoes);

            return products;
        } else
            return products;

    }


    public static List<CampaignStrategy> getCampaigns() {
        if (campaignStrategies == null) {
            campaignStrategies = new ArrayList<>();
            CampaignStrategy campaignAmount = new Campaign(food,10,2,DiscountType.AMOUNT);
            CampaignStrategy campaignRate = new Campaign(cloth, 10, 6, DiscountType.RATE);
            campaignStrategies.add(campaignAmount);
            campaignStrategies.add(campaignRate);
            return campaignStrategies;
        } else
            return campaignStrategies;

    }

    public static List<CouponStrategy> getCoupons() {
        if (couponStrategies == null) {
            couponStrategies = new ArrayList<>();
            CouponStrategy couponRate = new Coupon(100,15,DiscountType.RATE);
            CouponStrategy couponAmount = new Coupon(100,20,DiscountType.AMOUNT);
            couponStrategies.add(couponRate);
            couponStrategies.add(couponAmount);
            return couponStrategies;
        } else
            return couponStrategies;

    }
}
