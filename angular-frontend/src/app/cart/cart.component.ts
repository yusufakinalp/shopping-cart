import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  private baseUrl = 'http://localhost:8080';
  cart: any;
  products: any;

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit() {
    this.getCart();
    this.getProducts();
  }

  getCart() {
    this.http.get(this.baseUrl + '/api/cart').subscribe(data => {
      this.cart = data;
      console.log('Cart :');
      console.log(this.cart.products);
    });
  }

  getProducts() {
    this.http.get(this.baseUrl + '/api/cart-products').subscribe(data => {
      this.products = data;
      console.log('products :');
      console.log(this.products);
    });
  }

  completeShopping() {
    this.http.get(this.baseUrl + '/api/complete-shopping').subscribe(data => {
      this.router.navigate(['/products']);
    });
  }
}
