package com.yusuf.shoppingcart.delivery;

public class DeliveryCostCalculatorFactory {

    public IDeliveryCostCalculator getDeliveryCostCalculator(double costPerDelivery, double costPerProduct, double fixedCost) {
        return new DeliveryCostCalculator(costPerDelivery, costPerProduct, fixedCost);
    }
}
