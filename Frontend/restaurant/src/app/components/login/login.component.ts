import { CommonModule } from '@angular/common';

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { UserService } from 'src/app/services/user.service';
import { MaterialModule } from 'src/Material-Module';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, MaterialModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private service: UserService, private route: Router) { }

  ngOnInit(): void {
  }
  respdata: any;

  ProceedLogin(logindata: any) {
    if (logindata.valid) {
      this.service.ProceedLogin(logindata.value).subscribe(item => {
        this.respdata = item;
        if (this.respdata != null) {
          localStorage.setItem('token', this.respdata.access_token);
          this.route.navigate(['home']);
        } else {
          alert("Login failed");
        }
        //console.log(this.respdata);
      })
    }
    //console.log(logindata.value);
  }

  RedirectRegister() {
    this.route.navigate(['register']);
  }

}
