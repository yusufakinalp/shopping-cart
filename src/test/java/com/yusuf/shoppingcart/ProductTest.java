package com.yusuf.shoppingcart;

import com.yusuf.shoppingcart.product.Category;
import com.yusuf.shoppingcart.product.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class ProductTest {

    @Test
    public void categoryTest() {
        Category food = new Category("food");

        assertNotNull(food);
        assertEquals("food", food.getTitle());
    }

    @Test
    public void productTest() {
        Category electronic = new Category("tech");
        Product phone = new Product("phoneX", 123.4, electronic);

        assertNotNull(electronic);
        assertEquals("phoneX", phone.getTitle());
        assertEquals(electronic, phone.getCategory());
    }
}
