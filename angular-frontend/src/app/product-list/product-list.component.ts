import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  private baseUrl = 'http://localhost:8080';
  products: any;

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit() {
    this.http.get(this.baseUrl + '/api/products').subscribe(data => {
      this.products = data;
      console.log(this.products);
    });
  }

  addToCart(product) {
    console.log(product);
    this.http.post(this.baseUrl + '/api/add-item', product)
      .subscribe(res => {
          this.router.navigate(['/products']);
        }, (err) => {
          console.log(err);
        }
      );
  }
}
