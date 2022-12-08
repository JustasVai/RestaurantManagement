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

  ProceedLogin(inputData: any) {
    return this.http.post('http://localhost:8081/api/login', inputData);
  }

  IsLoggedIn() {
    return localStorage.getItem('token') != null;
  }
  GetToken() {
    return localStorage.getItem('token') != null ? localStorage.getItem('token') : '';
  }
  Registration(inputData: any) {
    return this.http.post('http://localhost:8081/api/register', inputData, { responseType: 'text' });
  }
  GetRole() {
    var token = localStorage.getItem('token');
    if (token != null) {
      var extractData = JSON.parse(atob(token?.split('.')[1]).toString());
      return extractData.roles;
    }
    else
    {
      return '';
    }

  }
}
