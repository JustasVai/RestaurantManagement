
import { Component, DoCheck, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

import { UserService } from '../services/user.service';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements DoCheck {

  

  constructor(private router:Router){

  }
  
  isMenuVisible =true;

  ngDoCheck(): void {
    const currentRoute = this.router.url;

    if((currentRoute=='/login') || (currentRoute=='/register')){
      this.isMenuVisible=false;
    }
    else{
      this.isMenuVisible=true;
    }
  }

  onLogout(){
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }
}
