package com.yusuf.shoppingcart;

import com.yusuf.shoppingcart.cart.Cart;
import com.yusuf.shoppingcart.product.Category;
import com.yusuf.shoppingcart.product.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class CartTest {

    Category cloth;
    Product shirt;
    Cart cart;

    @Before
    public void prepare() {
        cloth = new Category("cloth");
        shirt = new Product("shirt",25.0,cloth);
        cart = new Cart();
    }

    @Test
    public void cartTest() {
        cart.addItem(shirt,5);
        cart.addItem(shirt,3);

        assertNotNull(cart);
        assertEquals(8, cart.getProductsCount());
    }

    @Test
    public void totalAmountTest() {
        cart.addItem(shirt,4);
        cart.addItem(shirt,2);

        assertNotNull(cart);
        assertEquals(150, cart.getTotalAmount(), .9);
    }
}
