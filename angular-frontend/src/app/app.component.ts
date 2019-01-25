import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }
  campaigns: any;
  coupons: any;

  ngOnInit() {
    this.getCampaigns();
    this.getCoupons();
  }

  getCampaigns() {
    this.http.get(this.baseUrl + '/api/campaigns').subscribe(data => {
      this.campaigns = data;
      console.log('campaigns :');
      console.log(this.campaigns);
    });
  }

  getCoupons() {
    this.http.get(this.baseUrl + '/api/coupons').subscribe(data => {
      this.coupons = data;
      console.log('coupons :');
      console.log(this.coupons);
    });
  }
}
