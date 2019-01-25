package com.yusuf.shoppingcart.cart;

import com.yusuf.shoppingcart.discount.Campaign;
import com.yusuf.shoppingcart.discount.CampaignStrategy;
import com.yusuf.shoppingcart.discount.Coupon;
import com.yusuf.shoppingcart.discount.CouponStrategy;
import com.yusuf.shoppingcart.product.Category;

import java.util.HashMap;
import java.util.List;

public class CartDiscountCalculator {

    public double calculateCampaignDiscounts(Cart cart, List<CampaignStrategy> campaigns){
        double totalDiscount = 0;
        HashMap<Category, Double> categoryDiscount = new HashMap<>();

        for (CampaignStrategy campaign : campaigns) {
            double discount = campaign.applyDiscount(cart);
            Category campCat = campaign.getCategory();

            if (categoryDiscount.containsKey(campCat)) {
                if (categoryDiscount.get(campCat) < discount) {
                    categoryDiscount.put(campCat, discount);
                }
            } else {
                categoryDiscount.put(campCat, discount);
            }
        }
        for (HashMap.Entry<Category, Double> discount :
                categoryDiscount.entrySet()) {
            totalDiscount += discount.getValue();
        }
        return totalDiscount;
    }

    public double calculateCouponDiscount(Cart cart, List<CouponStrategy> coupons) {
        double maxDiscount = 0;

        for (CouponStrategy coupon : coupons) {
            double discount = coupon.applyDiscount(cart);

            if (discount > maxDiscount)
                maxDiscount = discount;
        }

        return maxDiscount;
    }
}
