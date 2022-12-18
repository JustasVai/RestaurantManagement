import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { HomeComponent } from './components/home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AccessRoutingModule } from './access/access-routing.module';
import { UserComponent } from './components/user/user.component';
import { NavbarComponent } from './navbar/navbar.component';
import { MaterialModule } from 'src/Material-Module';
import { TokenInterceptorService } from './services/token-interceptor.service';
import { ModalpopupComponent } from './modalpopup/modalpopup/modalpopup.component';
import { RestaurantComponent } from './components/restaurant/restaurant.component';
import { PopupComponent } from './components/restaurant/popup/popup.component';
import { MenuComponent } from './components/menu/menu.component';
import { MenuPopupComponent } from './components/menu/menu-popup/menu-popup.component';
import { MenuItemComponent } from './components/menu-item/menu-item.component';
import { ItemPopUpComponent } from './components/menu-item/item-pop-up/item-pop-up.component';
import { RecipePopupComponent } from './components/menu-item/recipe-popup/recipe-popup.component';




@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    UserComponent,
    NavbarComponent,
    ModalpopupComponent,
    RestaurantComponent,
    PopupComponent,
    MenuComponent,
   MenuPopupComponent,
   MenuItemComponent,
   ItemPopUpComponent,
   RecipePopupComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    AccessRoutingModule,
    MaterialModule,
  ReactiveFormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi:true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
