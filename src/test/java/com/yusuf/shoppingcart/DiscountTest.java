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


    }

    @Test
    public void discountTest_Accuracy_True() {
        cart = new Cart();
        food = new Category("food");
        cloth = new Category("cloth");
        shirt = new Product("shirt",25.0,cloth);
        campaignRate = new Campaign(cloth, 10, 6, DiscountType.RATE);
        campaignAmount = new Campaign(food, 10, 5, DiscountType.AMOUNT);

        couponRate = new Coupon(150,15,DiscountType.RATE);
        couponAmount = new Coupon(100,20,DiscountType.AMOUNT);

        cart.addItem(shirt, 6);
        cart.applyDiscounts(campaignAmount, campaignRate);
        cart.applyCoupon(couponAmount, couponRate);

        double campaignDiscount = cart.getCampaignDiscount();
        double couponDiscount = cart.getCouponDiscount();

        assertEquals(15.0, campaignDiscount, .9);
        assertEquals(22.5, couponDiscount, .9);
    }

    @Test
    public void discountTest2_Accuracy_True() {
        cart = new Cart();
        cloth = new Category("cloth");
        food = new Category("food");
        pineapple = new Product("pineapple",10,food);
        campaignRate = new Campaign(cloth, 10, 6, DiscountType.RATE);
        campaignAmount = new Campaign(food, 10, 5, DiscountType.AMOUNT);

        couponRate = new Coupon(150,15,DiscountType.RATE);
        couponAmount = new Coupon(100,20,DiscountType.AMOUNT);

        cart.addItem(pineapple, 10);
        cart.applyDiscounts(campaignAmount, campaignRate);
        cart.applyCoupon(couponAmount, couponRate);

        double campaignDiscount = cart.getCampaignDiscount();
        double couponDiscount = cart.getCouponDiscount();

        assertEquals(20.0, campaignDiscount, .9);
        assertEquals(20.0, couponDiscount, .9);
    }

    @Test(expected = RuntimeException.class)
    public void campaign_NegativeValue_ThrowException() {
        cloth = new Category("cloth");
        campaignRate = new Campaign(cloth, 10, -2, DiscountType.RATE);
    }

    @Test(expected = RuntimeException.class)
    public void coupon_NegativeValue_ThrowException() {
        cloth = new Category("cloth");
        campaignRate = new Campaign(cloth, -5, -2, DiscountType.RATE);
    }
}
