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

    @Test
    public void cart_Constructor_NotNull() {
        cloth = new Category("cloth");
        shirt = new Product("shirt",25.0,cloth);
        cart = new Cart();
        cart.addItem(shirt,5);
        cart.addItem(shirt,3);
        int productCount = cart.getProductsCount();
        assertNotNull(cart);
        assertEquals(8, productCount);
    }

    @Test
    public void totalAmount_Accuracy_True() {
        cloth = new Category("cloth");
        shirt = new Product("shirt",25.0,cloth);
        cart = new Cart();
        cart.addItem(shirt,4);
        cart.addItem(shirt,2);

        double total = cart.getTotalAmount();
        assertNotNull(cart);
        assertEquals(150, total, .9);
    }

    @Test(expected = RuntimeException.class)
    public void addItem_NegativeQuantity_ThrowsException() {
        cloth = new Category("cloth");
        shirt = new Product("shirt",25.0,cloth);
        cart = new Cart();
        cart.addItem(shirt,-1);
    }

    @Test
    public void totalAmount_CalculateEmptyCart_Zero() {
        cart = new Cart();
        cart.calculateTotalAmount();
        double result = cart.getTotalAmount();
        assertEquals(0, result, 0);
    }
}
