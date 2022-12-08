import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { MenuModel } from 'src/app/models/MenuModel';
import { MenuService } from 'src/app/services/menu.service';
import { MenuPopupComponent } from './menu-popup/menu-popup.component';
import * as alertify from 'alertifyjs';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit{

  constructor(private service:MenuService,private dialog: MatDialog, private route: ActivatedRoute,private router:Router){

  }  
  
  restaurantId: any;
  menuData!: MenuModel[];
  currentMenu: MenuModel[] = [];
  currentDate = new Date().toLocaleDateString();
  isAdmin = false;
  isEmployee = false;

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
        this.restaurantId = params['restaurant'];
   });
   this.LoadMenus();
   this.LoadRoles();
  }

  LoadMenus() {
    if(this.restaurantId!="")
    {
      this.service.GetAllMenus(this.restaurantId).subscribe(response => {
       this.menuData = response;
       this.currentMenu =[];
       this.menuData.forEach(element => {
        if(this.currentDate>new Date(element.startDate).toLocaleDateString() && this.currentDate<new Date(element.endDate).toLocaleDateString())
        {
          element.isActive="Active";
          this.currentMenu.push(element);
        }
        else
        {
          element.isActive="Not Active";
        }
       });
      })
    }
    
  }

  OpenPopUp(id: any) {
    const _popup = this.dialog.open(MenuPopupComponent, {
      width: '500px',
      exitAnimationDuration: '1000ms',
      enterAnimationDuration: '1000ms',
      data: {
        id: id,
        restaurantId:this.restaurantId,
      }
    })
    _popup.afterClosed().subscribe(r => {
      this.LoadMenus();
    })
  }
  OpenMenu(id:string){
    this.router.navigate(['menu-item',id,this.restaurantId]);
  }
  EditMenu(menuid: any){
    this.OpenPopUp(menuid);
  }
  RemoveMenu(id: any) {
    alertify.confirm("Remove Menu ", `Are you sure you want to remove menu with id = ${id}?`, () => {
      this.service.RemoveMenu(id,this.restaurantId).subscribe(response => {
        this.LoadMenus();
      })
    }, function () {

    })

  }
 LoadRoles(){
    this.isAdmin = localStorage.getItem("isAdmin")=="true";
    this.isEmployee = localStorage.getItem("isEmployee")=="true";
  }
}
