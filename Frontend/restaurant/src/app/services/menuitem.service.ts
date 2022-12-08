import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MenuItemModel } from '../models/MenuItemModel';

@Injectable({
  providedIn: 'root'
})
export class MenuitemService {

  constructor(private http: HttpClient) { }
  apiUrl = 'http://localhost:8081/api/v1/restaurants/';

  GetAllMenuItems(restaurantid:string,menuid:string):Observable<MenuItemModel[]>{
    return this.http.get<MenuItemModel[]>(this.apiUrl+restaurantid+"/menus/"+menuid+"/items");
  }
  GetItemById(restaurantid:string,menuid:string,itemid:string){
    return this.http.get<MenuItemModel>(this.apiUrl+restaurantid+"/menus/"+menuid+"/items/"+itemid);
  }
  CreateMenuItem(restaurantid:string,menuid:string,item:any){
    console.log(menuid);
    return this.http.post(this.apiUrl+restaurantid+"/menus/"+menuid+"/items",item);
  }
  UpdateItem(restaurantid:string,menuid:string,itemid:string,item:any){
    return this.http.put(this.apiUrl+restaurantid+"/menus/"+menuid+"/items/"+itemid,item);
  }
  RemoveItem(restaurantid:string,menuid:string,itemid:string){
    return this.http.delete(this.apiUrl+restaurantid+"/menus/"+menuid+"/items/"+itemid);
  }
}
