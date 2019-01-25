package com.yusuf.shoppingcart;

import com.yusuf.shoppingcart.cart.Cart;
import com.yusuf.shoppingcart.delivery.DeliveryCostCalculatorFactory;
import com.yusuf.shoppingcart.delivery.IDeliveryCostCalculator;
import com.yusuf.shoppingcart.discount.*;
import com.yusuf.shoppingcart.product.Category;
import com.yusuf.shoppingcart.product.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class DeliveryTest {

    Category cloth, food;
    Product shirt, pineapple;
    Cart cart;
    IDeliveryCostCalculator deliveryCostCalculator;

    @Test
    public void calculateDeliveryCost_Accuracy_True() {
        cloth = new Category("cloth");
        food = new Category("food");
        shirt = new Product("shirt",25.0,cloth);
        pineapple = new Product("pineapple", 10.0, food);
        cart = new Cart();
        deliveryCostCalculator = new DeliveryCostCalculatorFactory().getDeliveryCostCalculator(2.0,2.0,2.0);

        cart.addItem(shirt, 6);

        double cost = deliveryCostCalculator.calculateFor(cart);

        assertEquals(6.0, cost, 0.9);
    }

    @Test
    public void calculateDeliveryCost2_Accuracy_True() {
        cloth = new Category("cloth");
        food = new Category("food");
        shirt = new Product("shirt",25.0,cloth);
        pineapple = new Product("pineapple", 10.0, food);
        cart = new Cart();
        deliveryCostCalculator = new DeliveryCostCalculatorFactory().getDeliveryCostCalculator(3.0,2.0,2.0);
        cart.addItem(pineapple,5);
        cart.addItem(shirt, 6);

        double cost = deliveryCostCalculator.calculateFor(cart);

        assertEquals(12.0, cost, 0.9);
    }
}
