package com.yusuf.shoppingcart;

import com.yusuf.shoppingcart.cart.Cart;
import com.yusuf.shoppingcart.discount.*;
import com.yusuf.shoppingcart.product.Category;
import com.yusuf.shoppingcart.product.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class DiscountTest {

    Category cloth, food;
    Product shirt, pineapple;
    Cart cart;
    CampaignStrategy campaignAmount;
    CampaignStrategy campaignRate;

    CouponStrategy couponRate;
    CouponStrategy couponAmount;

    @Before
    public void prepare() {
        cloth = new Category("cloth");
        food = new Category("food");
        shirt = new Product("shirt",25.0,cloth);
        pineapple = new Product("pineapple",10,food);
        cart = new Cart();

        campaignRate = new Campaign(cloth, 10, 6, DiscountType.RATE);
        campaignAmount = new Campaign(food, 10, 5, DiscountType.AMOUNT);

        couponRate = new Coupon(150,15,DiscountType.RATE);
        couponAmount = new Coupon(100,20,DiscountType.AMOUNT);
    }

    @Test
    public void discountTest() {
        cart.addItem(shirt, 6);
        cart.applyDiscounts(campaignAmount, campaignRate);
        cart.applyCoupon(couponAmount, couponRate);

        assertEquals(15.0, cart.getCampaignDiscount(), .9);
        assertEquals(22.5, cart.getCouponDiscount(), .9);
    }

    @Test
    public void discountTest2() {
        cart.addItem(pineapple, 10);
        cart.applyDiscounts(campaignAmount, campaignRate);
        cart.applyCoupon(couponAmount, couponRate);

        assertEquals(20.0, cart.getCampaignDiscount(), .9);
        assertEquals(20.0, cart.getCouponDiscount(), .9);
    }
}
