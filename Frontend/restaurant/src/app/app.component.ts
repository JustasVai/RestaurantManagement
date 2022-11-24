import { Component, DoCheck, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from './services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements DoCheck{
  

  isAdmin  = false;
  constructor(private service : UserService){

  }
  
  ngDoCheck(): void {
    
  }


  title = 'restaurant';
 

 
}
