import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private fb: FormBuilder, private auth:AuthService, private router:Router) { }
  signUpForm! :FormGroup;
  
  ngOnInit(): void {
    this.signUpForm = this.fb.group({
      username: ['', Validators.required],
      email:  ['',Validators.required],
      password: ['', Validators.required]
    })
  }
  onSignUp() {
    if (this.signUpForm.valid) {
      console.log(this.signUpForm.value)
      this.auth.signUp(this.signUpForm.value).subscribe({
        next:() =>{
          //alert(res.value);
          this.signUpForm.reset();
          this.router.navigate(['/login']);
        }
      })
    }
    else {
      this.validateAllFormFields(this.signUpForm);
    }
  }
  private validateAllFormFields(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach(field => {
      const control = formGroup.get(field);
      if (control instanceof FormControl) {
        control.markAsDirty({ onlySelf: true })
      }
      else if (control instanceof FormGroup) {
        this.validateAllFormFields(control)
      }
    })
  }
}
