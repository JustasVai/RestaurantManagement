import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserModel } from '../models/UserModel';

@Injectable({
  providedIn: 'root'
})
export class UserMasterService {

  constructor(private http: HttpClient) { }
  apiUrl = "http://localhost:8081/api/users"
  GetAllUser():Observable<UserModel[]> {
    return this.http.get<UserModel[]>(this.apiUrl);
  }
  GetUser(username: any) {
    return this.http.get(this.apiUrl + '/' + username);
  }
  AddRoleToUser(inputData:any){
    return this.http.post('http://localhost:8081/api/role/addToUser',inputData,{ observe: 'response' });
  }
}
