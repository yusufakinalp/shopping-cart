package com.yusuf.shoppingcart.delivery;

import com.yusuf.shoppingcart.cart.Cart;
import com.yusuf.shoppingcart.product.Category;
import com.yusuf.shoppingcart.product.Product;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DeliveryCostCalculator implements IDeliveryCostCalculator {

    private double costPerDelivery;

    private double costPerProduct;

    private double fixedCost;

    public DeliveryCostCalculator(double costPerDelivery, double costPerProduct, double fixedCost) {
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costPerProduct;
        this.fixedCost = fixedCost;
    }

    @Override
    public double calculateFor(Cart cart) {
        int numberOfDeliveries = calculateNumberOfDeliveries(cart);
        int numberOfProducts = cart.getProducts().size();

        double result = (costPerDelivery * numberOfDeliveries) + (costPerProduct * numberOfProducts) + fixedCost;

        return result;
    }

    private int calculateNumberOfDeliveries(Cart cart) {
        Set<Category> categories = new HashSet<>();

        for (Map.Entry<Product, Integer> products:
                cart.getProducts().entrySet()) {
            categories.add(products.getKey().getCategory());
        }

        return categories.size();
    }
}
