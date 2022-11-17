import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  logout() {
    throw new Error('Method not implemented.');
  }

  constructor(private http: HttpClient) { 

  }

  ProceedLogin(inputData:any){
    return this.http.post('http://test-env.eba-fmypdxa8.us-east-1.elasticbeanstalk.com/api/login',inputData);
  }

  IsLoggedIn(){
    return localStorage.getItem('token')!=null;
  }
  GetToken(){
    return localStorage.getItem('token')!=null?localStorage.getItem('token'):'';
  }
  Registration(inputData:any){
    return this.http.post('http://test-env.eba-fmypdxa8.us-east-1.elasticbeanstalk.com/api/register',inputData, { responseType: 'text' });
  }
}
