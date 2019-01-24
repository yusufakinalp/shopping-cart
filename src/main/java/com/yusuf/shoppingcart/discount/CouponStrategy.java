package com.yusuf.shoppingcart.discount;

import com.yusuf.shoppingcart.cart.Cart;

public interface CouponStrategy {
    double applyDiscount(Cart cart);
}
