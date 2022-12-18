
import { Component, DoCheck, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { throws } from 'assert';
import { Observable } from 'rxjs';

import { UserService } from '../services/user.service';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements DoCheck ,OnInit{

  isAdmin  = false;
  isEmployee = false;
  currentRoles:any;

  constructor(private router:Router,private service:UserService){

  }
  
  isMenuVisible =true;
  ngOnInit():void{
   
  }
  ngDoCheck(): void {
    const currentRoute = this.router.url;
    this.MenuDisplay();
    if((currentRoute=='/login') || (currentRoute=='/register')){
      this.isMenuVisible=false;
    }
    else{
      this.isMenuVisible=true;
    }
  }

  onLogout(){
    localStorage.removeItem('token');
    localStorage.removeItem('refresh_token');
    localStorage.removeItem('isAdmin');
    localStorage.removeItem('isEmployee');
    this.router.navigate(['/login']);
  }
  MenuDisplay(){
    if(this.service.GetRole()!="")
    {
      this.currentRoles = this.service.GetRole();
      this.currentRoles.forEach((element: string) => {
        if(element=="ROLE_EMPLOYEE")
        {
          this.isEmployee = true;
          localStorage.setItem("isEmployee","true");
        }
        if(element=="ROLE_ADMIN")
        {
          this.isAdmin = true;
          localStorage.setItem("isAdmin","true");
        }
      });
   
    }
    else
    {
      this.isAdmin = false;
      this.isEmployee = false;
    }
  }
}
