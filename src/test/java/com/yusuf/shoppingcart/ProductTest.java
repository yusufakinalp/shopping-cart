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
    public void category_Title_Equal() {
        Category food = new Category("food");

        String title = food.getTitle();
        assertNotNull(food);
        assertEquals("food", title);
    }

    @Test
    public void product_Title_Equal() {
        Category electronic = new Category("tech");
        Product phone = new Product("phoneX", 123.4, electronic);

        String title = phone.getTitle();
        Category category = phone.getCategory();
        assertNotNull(electronic);
        assertEquals("phoneX", title);
        assertEquals(electronic, category);
    }

    @Test(expected = Exception.class)
    public void category_TitleBlank_ThrownException() {
        Category food = new Category("");
    }

    @Test(expected = Exception.class)
    public void product_NegativePrice_ThrownException() {
        Category electronic = new Category("tech");
        Product phone = new Product("phoneX", -123.4, electronic);
    }
}
