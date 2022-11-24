import { HttpClient, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor{

  constructor(private http: HttpClient,private service:UserService) { }

  
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

     req=req.clone({
        setHeaders:{
          Authorization:`Bearer ${this.service.GetToken()}`,

        }
    });

    return next.handle(req);
  }
}
