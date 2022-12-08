import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { MenuItemModel } from 'src/app/models/MenuItemModel';
import { MenuitemService } from 'src/app/services/menuitem.service';
import { ItemPopUpComponent } from './item-pop-up/item-pop-up.component';
import { RecipePopupComponent } from './recipe-popup/recipe-popup.component';
import * as alertify from 'alertifyjs';
@Component({
  selector: 'app-menu-item',
  templateUrl: './menu-item.component.html',
  styleUrls: ['./menu-item.component.css']
})
export class MenuItemComponent implements OnInit{

  menuItems: MenuItemModel[]=[];
  restaurantId:any;
  menuId:any;
  isAdmin = false;
  isEmployee = false;

  constructor(private service:MenuitemService,private dialog: MatDialog, private route: ActivatedRoute,private router:Router){

    this.route.params.subscribe((params: Params) => {
      this.restaurantId = params['restaurant'];
      this.menuId = params['menu'];
    });

  }
  ngOnInit(): void {
    console.log(this.restaurantId);
    console.log(this.menuId);
    this.LoadMenuItems();
    this.LoadRoles();
  }

  LoadRoles(){
    this.isAdmin = localStorage.getItem("isAdmin")=="true";
    this.isEmployee = localStorage.getItem("isEmployee")=="true";
  }

  LoadMenuItems() {
    if(this.restaurantId!="")
    {
       this.service.GetAllMenuItems(this.restaurantId,this.menuId).subscribe(response => {
       this.menuItems = response;
       console.log(response);
     
      })
    }
    
  }

  OpenPopUp(id: any) {
    const _popup = this.dialog.open(ItemPopUpComponent, {
      width: '500px',
      exitAnimationDuration: '1000ms',
      enterAnimationDuration: '1000ms',
      data: {
        id: id,
        restaurantId:this.restaurantId,
        menuId:this.menuId
      }
    })
    _popup.afterClosed().subscribe(r => {
      this.LoadMenuItems();
    })
  }
  EditItem(id:string){
    this.OpenPopUp(id);
  }
  DeleteItem(id:string){
    alertify.confirm("Remove Menu Item", `Are you sure you want to remove menu item with id = ${id}?`, () => {
      this.service.RemoveItem(this.restaurantId,this.menuId,id).subscribe(response => {
        this.LoadMenuItems();
      })
    }, function () {

    })

  }
  OpenRecipe(recipe:string){
    this.dialog.open(RecipePopupComponent,{
      width: '500px',
      exitAnimationDuration: '1000ms',
      enterAnimationDuration: '1000ms',
      data: {
        recipe:recipe
      }
    })
  }
}
