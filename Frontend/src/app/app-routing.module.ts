import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccessModule } from './access/access.module';
import { RegisterComponent } from './access/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RestaurantComponent } from './components/restaurant/restaurant.component';
import { UserComponent } from './components/user/user.component';

import { AuthGuard } from './guard/auth.guard';
import { MenuComponent } from './components/menu/menu.component';
import { MenuItemComponent } from './components/menu-item/menu-item.component';


const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full' },
  {
    path: 'access',
    component: AccessModule
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'user',
    component: UserComponent, canActivate: [AuthGuard]
  },
  {
    path: 'home',
    component: HomeComponent, canActivate: [AuthGuard]
  },
  {
    path: 'restaurant',
    component: RestaurantComponent, canActivate: [AuthGuard],
  },
  {
    path: 'menu/:restaurant',
    component: MenuComponent, canActivate: [AuthGuard]
  },
  {
    path: 'menu-item/:menu/:restaurant',
    component: MenuItemComponent, canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
