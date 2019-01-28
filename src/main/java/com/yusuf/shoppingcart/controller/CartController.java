package com.yusuf.shoppingcart.controller;

import com.yusuf.shoppingcart.cart.Cart;
import com.yusuf.shoppingcart.controller.service.CartService;
import com.yusuf.shoppingcart.delivery.DeliveryCostCalculatorFactory;
import com.yusuf.shoppingcart.delivery.IDeliveryCostCalculator;
import com.yusuf.shoppingcart.discount.CampaignStrategy;
import com.yusuf.shoppingcart.discount.CouponStrategy;
import com.yusuf.shoppingcart.product.Product;
import com.yusuf.shoppingcart.controller.service.ProductService;
import com.yusuf.shoppingcart.product.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CartController {

    @GetMapping(path = "/products")
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<Product>(ProductService.getProducts().values());
        return list;
    }

    @GetMapping(path = "/cart")
    public ResponseEntity<Cart> getCart() {
        Cart cart = CartService.getCart();
        cart.calculateTotalAmount();
        cart.applyDiscounts(ProductService.getCampaigns());
        cart.applyCoupon(ProductService.getCoupons());
        IDeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculatorFactory().getDeliveryCostCalculator(2.0,2.0,2.45);
        cart.setDeliveryCost(deliveryCostCalculator.calculateFor(cart));
        return ResponseEntity.ok(cart);
    }

    @PostMapping(path = "/add-item")
    public ResponseEntity<?> addItem(@RequestBody Product product) {
        Cart cart = CartService.getCart();
        Product temp = ProductService.getProducts().get(product.getTitle());
        cart.addItem(temp, 1);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/remove-item")
    public ResponseEntity<?> removeItem(@RequestBody Product product) {
        Cart cart = CartService.getCart();
        Product temp = ProductService.getProducts().get(product.getTitle());
        cart.removeItem(temp, 1);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/campaigns")
    public List<CampaignStrategy> getCampaigns() {
        return ProductService.getCampaigns();
    }

    @GetMapping(path = "/coupons")
    public List<CouponStrategy> getCoupons() {
        return ProductService.getCoupons();
    }

    @GetMapping(path = "/cart-products")
    public List<ProductDTO> getCartProducts() {
        return CartService.getCart().getSortedProducts();
    }

    @GetMapping(path = "/complete-shopping")
    public ResponseEntity<?> completeShopping() {
        CartService.completeCart();
        return ResponseEntity.ok().build();
    }
}
