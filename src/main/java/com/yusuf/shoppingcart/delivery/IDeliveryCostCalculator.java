package com.yusuf.shoppingcart.delivery;

import com.yusuf.shoppingcart.cart.Cart;

public interface IDeliveryCostCalculator {

    double calculateFor(Cart cart);
}
