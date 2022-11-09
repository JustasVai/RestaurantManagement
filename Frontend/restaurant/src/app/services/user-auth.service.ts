import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  constructor() { }

  public setRoles(roles: []) {
    localStorage.setItem("roles", JSON.stringify(roles));
  }

  public getRoles(): [] {
    return JSON.parse(localStorage.getItem('roles') || '{}');
  }

  public setToken(jwtToken: string) {
    localStorage.setItem("access_token", jwtToken);
  }

  public getToken(): string {
    return localStorage.getItem('access_token') || "";
  }

  public clear() {
    localStorage.clear();
  }

  public isLoggedIn() {
    return this.getToken();
  }
}
