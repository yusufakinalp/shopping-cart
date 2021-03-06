package com.yusuf.shoppingcart.cart;

import com.yusuf.shoppingcart.discount.CampaignStrategy;
import com.yusuf.shoppingcart.discount.CouponStrategy;
import com.yusuf.shoppingcart.product.Category;
import com.yusuf.shoppingcart.product.Product;
import com.yusuf.shoppingcart.product.ProductDTO;

import java.util.*;

public class Cart {

    private Map<Product, Integer> products;

    private Set<Category> categories;

    private int productsCount;

    private double totalAmount;

    private double totalAmountAfterDiscount;

    private double couponDiscount;

    private double campaignDiscount;

    private double deliveryCost;

    public Cart() {
        products = new HashMap<>();
        categories = new HashSet<>();
        productsCount = 0;
        couponDiscount = 0;
        campaignDiscount = 0;
    }

    public void addItem(Product product, int quantity) {
        if (quantity < 0)
            throw new RuntimeException("Quantity cannot be negative in addItem method");
        productsCount += quantity;
        categories.add(product.getCategory());
        if (products.containsKey(product)) {
            quantity += products.get(product);
            products.put(product, quantity);
        } else {
            products.put(product, quantity);
        }
        calculateTotalAmount();
    }

    public void removeItem(Product product, int quantity) {
        if (products.containsKey(product)) {
            int productQuantity = products.get(product);
            int result = productQuantity - quantity;
            if (result == 0) {
                products.remove(product);
            } else if (result > 0) {
                products.put(product, result);
            } else if (result < 0) {
                throw new RuntimeException("Removing too many items at once");
            }
        } else {
            throw new RuntimeException("There is no such product in the cart");
        }
        productsCount -= quantity;
        calculateTotalAmount();
    }

    public void calculateTotalAmount() {
        double total = 0;

        for (Map.Entry<Product, Integer> product :
                products.entrySet()) {
            total += product.getKey().getPrice() * product.getValue();
        }

        totalAmount = total;
    }

    public void applyDiscounts(List<CampaignStrategy> campaigns) {
        campaignDiscount = new CartDiscountCalculator().calculateCampaignDiscounts(this, campaigns);
    }

    public void applyCoupon(List<CouponStrategy> coupons) {
        couponDiscount = new CartDiscountCalculator().calculateCouponDiscount(this, coupons);
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public int getProductsCount() {
        return productsCount;
    }

    public double getCategoryProductsTotal(Category category) {
        double total = 0;
        for (Map.Entry<Product, Integer> item : products.entrySet()) {
            if (category.equals(item.getKey().getCategory()))
                total += item.getKey().getPrice() * item.getValue();
        }
        return total;
    }

    public int getCategoryProductsCount(Category category) {
        int count = 0;
        for (Map.Entry<Product, Integer> item : products.entrySet()) {
            if (category.equals(item.getKey().getCategory()))
                count = item.getValue();
        }
        return count;
    }

    public double getTotalAmount() {
        calculateTotalAmount();
        return totalAmount;
    }

    public double getTotalAmountAfterDiscount() {
        return totalAmount - campaignDiscount - couponDiscount;
    }

    public double getCouponDiscount() {
        return couponDiscount;
    }

    public double getCampaignDiscount() {
        return campaignDiscount;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public void print() {
        String divider = "--------------------------------------------------";

        for (Category category: categories) {
            System.out.println(category.getTitle());
            System.out.println(divider);
            for (Map.Entry<Product, Integer> item : products.entrySet()) {
                if (category.equals(item.getKey().getCategory())) {
                    System.out.printf("%-20s %15d %10.2f %n",item.getKey().getTitle(), item.getValue(), item.getKey().getPrice());
                }
            }
            System.out.println(divider);
        }
        System.out.printf("Total Price                        : %10.2f %n", getTotalAmount());
        System.out.printf("Campaign Discount                  : %10.2f %n", getCampaignDiscount());
        System.out.printf("Coupon Discount                    : %10.2f %n", getCouponDiscount());
        System.out.printf("Total Amount After Discount        : %10.2f %n", getTotalAmountAfterDiscount());
        System.out.printf("Delivery Cost                      : %10.2f %n", getDeliveryCost());
    }

    public List<ProductDTO> getSortedProducts() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Category category: categories) {
            for (Map.Entry<Product, Integer> item : products.entrySet()) {
                if (category.equals(item.getKey().getCategory())) {
                    productDTOList.add(new ProductDTO(item.getKey().getTitle(),item.getValue(), category.getTitle(),item.getKey().getPrice()));
                }
            }
        }
        return productDTOList;
    }
}
