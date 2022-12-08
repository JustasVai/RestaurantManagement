import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RestaurantModel } from '../models/RestaurantModel';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  constructor(private http: HttpClient) { }
  apiUrl = 'http://localhost:8081/api/v1/restaurants'

  GetAllRestaurants():Observable<RestaurantModel[]>{
    return this.http.get<RestaurantModel[]>(this.apiUrl);
  }

  GetRestaurantById(id:any):Observable<RestaurantModel>{
    return this.http.get<RestaurantModel>(this.apiUrl+'/'+id);
  }

  RemoveRestaurant(id:any){
    return this.http.delete(this.apiUrl+'/'+id);
  }
  
  UpdateRestaurant(id:any,restaurant:any){
    return this.http.put(this.apiUrl+'/'+id,restaurant);
  }
  

  CreateRestaurant(restaurant:any)
  {
    return this.http.post(this.apiUrl,restaurant);
  }
  GetRandomImage()
  {
    return this.http.get("https://picsum.photos/id/870/200/300?grayscale&blur=2")
  }
}
