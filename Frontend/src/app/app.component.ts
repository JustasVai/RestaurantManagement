import { Component, DoCheck, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { UserService } from './services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements DoCheck,OnInit{
  


  isMenuVisible =true;

  constructor(private service : UserService,private router:Router){

  }
  
  ngDoCheck(): void {
    const currentRoute = this.router.url;

    if((currentRoute=='/login') || (currentRoute=='/register')){
      this.isMenuVisible=false;
    }
    else{
      this.isMenuVisible=true;
    }
  }

  ngOnInit():void{
    
  }

  title = 'restaurant';

  onLogout(){
    localStorage.removeItem('token');

    this.router.navigate(['/login']);
  }
 
 
}
