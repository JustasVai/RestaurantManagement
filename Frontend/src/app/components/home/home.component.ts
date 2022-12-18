import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  message = 'Hi';
  constructor( private http: HttpClient,private route:Router) {

  }

  ngOnInit(): void {
 
  }
  RedirectRestaurants() {
    this.route.navigate(['restaurant']);
  }

}
