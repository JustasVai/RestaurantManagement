import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import * as alertify from "alertifyjs"

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private router: Router,private service: UserService) { }

  ngOnInit(): void {
  }

  respdata:any;

  RedirectLogin() {
    this.router.navigate(["login"]);
  }

  reactiveform = new FormGroup({
    username: new FormControl('',Validators.required),
    email: new FormControl('',Validators.email),
    password: new FormControl('',Validators.required)
  })
  SaveUser(){
    if(this.reactiveform.valid)
    {
      this.service.Registration(this.reactiveform.value).subscribe((item)=>{
        this.respdata = item;
        if(this.respdata == "User registered success")
        {
          alert("Registration successful")
          this.RedirectLogin();
        }
        
      }, (err)=>{
       
        alert("Username or email is already in use")
        
      }
      );
    }
  } 
}
