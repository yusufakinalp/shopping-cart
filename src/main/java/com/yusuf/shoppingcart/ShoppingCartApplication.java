package com.yusuf.shoppingcart;

import com.yusuf.shoppingcart.cart.Cart;
import com.yusuf.shoppingcart.delivery.DeliveryCostCalculatorFactory;
import com.yusuf.shoppingcart.delivery.IDeliveryCostCalculator;
import com.yusuf.shoppingcart.discount.*;
import com.yusuf.shoppingcart.product.Category;
import com.yusuf.shoppingcart.product.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShoppingCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}

	@Bean
	CommandLineRunner init() {

		return args -> {

			Category food = new Category("food");
			Category cloth = new Category("cloth");

			Product apple = new Product("apple",5.0,food);
			Product shirt = new Product("shirt",25.0,cloth);

			Cart cart = new Cart();

			cart.addItem(apple,5);
			cart.addItem(shirt, 7);

			CampaignStrategy campaignAmount = new Campaign(food,10,2,DiscountType.AMOUNT);
			CampaignStrategy campaignRate = new Campaign(cloth, 10, 6, DiscountType.RATE);

			CouponStrategy couponRate = new Coupon(100,15,DiscountType.RATE);
			CouponStrategy couponAmount = new Coupon(100,20,DiscountType.AMOUNT);

			cart.applyDiscounts(campaignAmount, campaignRate);
			cart.applyCoupon(couponAmount,couponRate);
			IDeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculatorFactory().getDeliveryCostCalculator(2.0,2.0,2.45);
			cart.setDeliveryCost(deliveryCostCalculator.calculateFor(cart));

			cart.print();

		};
	}
}

