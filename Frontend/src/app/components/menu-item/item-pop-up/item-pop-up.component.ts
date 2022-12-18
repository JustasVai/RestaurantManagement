import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MenuitemService } from 'src/app/services/menuitem.service';
import * as alertify from 'alertifyjs'
@Component({
  selector: 'app-item-pop-up',
  templateUrl: './item-pop-up.component.html',
  styleUrls: ['./item-pop-up.component.css']
})
export class ItemPopUpComponent implements OnInit {
  
  editItem:any;

  ngOnInit(): void {
      if (this.data.id != '' && this.data.id != null) {
        this.service.GetItemById(this.data.restaurantId,this.data.menuId,this.data.id).subscribe(response => {
        this.editItem = response;
        this.menuItemForm.setValue({ id: this.editItem.id, name: this.editItem.name, price: this.editItem.price, recipe: this.editItem.recipe, description: this.editItem.description})
      })
    }
    console.log(this.data.restaurantId)
    console.log(this.data.menuId)
  }
  constructor(private builder: FormBuilder, private dialog: MatDialog, private service: MenuitemService, @Inject(MAT_DIALOG_DATA) public data: any) { }

  menuItemForm = this.builder.group({
    id: this.builder.control({ value: '', disabled: true }),
    name: this.builder.control('', Validators.required),
    price: this.builder.control('', Validators.required),
    recipe: this.builder.control('', Validators.required),
    description: this.builder.control('', Validators.required),
  })

  ClosePopUp() {
    this.dialog.closeAll();
  }
  SaveMenuItem() {
    if (this.menuItemForm.valid) {
      const editId = this.menuItemForm.getRawValue().id;
      
      if (editId != '' && editId != null) {
        this.service.UpdateItem(this.data.restaurantId,this.data.menuId,editId,this.menuItemForm.getRawValue()).subscribe(response => {
          this.ClosePopUp();
          alertify.success(`Menu Item ${this.menuItemForm.value.name} updated succesfully`);
        })
      } else {
        this.service.CreateMenuItem(this.data.restaurantId,this.data.menuId,this.menuItemForm.value,).subscribe(response => {
            console.log(this.menuItemForm.value)
           
          this.ClosePopUp();
          alertify.success(`Menu Item ${this.menuItemForm.value.name} created succesfully`);
        })
      }

    }
  }
}
