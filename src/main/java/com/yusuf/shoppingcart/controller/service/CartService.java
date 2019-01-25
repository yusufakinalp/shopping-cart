package com.yusuf.shoppingcart.controller.service;

import com.yusuf.shoppingcart.cart.Cart;
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
}
