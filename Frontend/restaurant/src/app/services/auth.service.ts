import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
@Injectable({
  providedIn: 'root'
})
export class AuthService {


  private baseUrl: string = "http://test-env.eba-fmypdxa8.us-east-1.elasticbeanstalk.com/api/"
  //private baseUrl:string = "http://localhost:5000/api/"
  constructor(private http: HttpClient) { }

  signUp(userObj: any) {
    return this.http.post(`${this.baseUrl}register`, userObj, {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text'
    });
  }

  login(loginObj: any) {
    return this.http.post<any>(`${this.baseUrl}login`, loginObj, {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
    });
  }
  getUser(userId:any){
    return this.http.get(`${this.baseUrl}users/${userId}`);
  }
}

