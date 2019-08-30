import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Cart } from './cart';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  private baseUrl = 'http://localhost:8080';
  cart: Cart;
  products: any;

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit() {
    this.getCart();
    this.getProducts();
  }

  getCart() {
    this.http.get<Cart>(this.baseUrl + '/api/cart').subscribe(data => {
      this.cart = data;
      console.log('Cart :');
      console.log(this.cart);
    });
  }

  getProducts() {
    this.http.get(this.baseUrl + '/api/cart-products').subscribe(data => {
      this.products = data;
      console.log('products :');
      console.log(this.products);
    });
  }

  addToCart(product) {
    console.log(product);
    this.http.post(this.baseUrl + '/api/add-item', product)
      .subscribe(res => {
        this.getProducts();
        this.getCart();
        }, (err) => {
          console.log(err);
        }
      );
  }

  removeFromCart(product) {
    this.http.post(this.baseUrl + '/api/remove-item', product)
      .subscribe(res => {
          this.getProducts();
          this.getCart();
        }, (err) => {
          console.log(err);
        }
      );
  }

  completeShopping() {
    this.http.get(this.baseUrl + '/api/complete-shopping').subscribe(data => {
      this.router.navigate(['/products']);
    });
  }
}
