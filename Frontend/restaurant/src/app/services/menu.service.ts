import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MenuModel } from '../models/MenuModel';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  constructor(private http: HttpClient) { }
  apiUrl = 'http://localhost:8081/api/v1/restaurants/';
  GetAllMenus(id:string):Observable<MenuModel[]>{
    return this.http.get<MenuModel[]>(this.apiUrl+id+"/menus");
  }
  CreateMenu(menu:any,restaurantId:string)
  {
    return this.http.post(this.apiUrl+restaurantId+"/menus",menu);
  }
  GetMenuById(menuid:string, restaurantid:string)
  {
    return this.http.get(this.apiUrl+restaurantid+"/menus/"+menuid);
  }
  UpdateMenu(menuid:string,restaurantid:string,menu:any)
  {
    return this.http.put(this.apiUrl+restaurantid+"/menus/"+menuid,menu);
  }
  RemoveMenu(menuid:string,restaurantid:string){
    return this.http.delete(this.apiUrl+restaurantid+"/menus/"+menuid)
  }
}
