package com.yusuf.shoppingcart.controller.service;

import com.yusuf.shoppingcart.cart.Cart;
import com.yusuf.shoppingcart.cart.CartDTO;
import com.yusuf.shoppingcart.delivery.DeliveryCostCalculatorFactory;
import com.yusuf.shoppingcart.delivery.IDeliveryCostCalculator;
import org.springframework.stereotype.Service;


public class CartService {

    private static Cart cart;

    public static Cart getCart() {
        if (cart == null) {
            cart = new Cart();
            return cart;
        }
        else
            return cart;
    }

    public static void completeCart() {
        cart = null;
    }

    public static CartDTO getCartDTO() {
        getCart();
        cart.calculateTotalAmount();
        cart.applyDiscounts(ProductService.getCampaigns());
        cart.applyCoupon(ProductService.getCoupons());
        IDeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculatorFactory().getDeliveryCostCalculator(2.0,2.0,2.45);
        cart.setDeliveryCost(deliveryCostCalculator.calculateFor(cart));

        CartDTO cartDTO = new CartDTO(cart.getTotalAmount(),
                                        cart.getCampaignDiscount(),
                                        cart.getCouponDiscount(),
                                        cart.getTotalAmountAfterDiscount(),
                                        cart.getDeliveryCost());
        return cartDTO;
    }
}
