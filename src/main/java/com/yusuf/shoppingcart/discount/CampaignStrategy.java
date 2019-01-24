package com.yusuf.shoppingcart.discount;

import com.yusuf.shoppingcart.cart.Cart;
import com.yusuf.shoppingcart.product.Category;

public interface CampaignStrategy {

    Category getCategory();

    double applyDiscount(Cart cart);
}
