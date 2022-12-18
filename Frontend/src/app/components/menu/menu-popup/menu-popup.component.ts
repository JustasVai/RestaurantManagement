import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators} from '@angular/forms';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MenuService } from 'src/app/services/menu.service';
import * as alertify from 'alertifyjs'
@Component({
  selector: 'app-popup',
  templateUrl: './menu-popup.component.html',
  styleUrls: ['./menu-popup.component.css']
})
export class MenuPopupComponent implements OnInit {
  editMenu: any;

  ngOnInit(): void {
    if (this.data.id != '' && this.data.id != null) {
        this.service.GetMenuById(this.data.id,this.data.restaurantId).subscribe(response => {
        this.editMenu = response;
        this.menuForm.setValue({ id: this.editMenu.id, title: this.editMenu.title, startDate: this.editMenu.startDate, endDate: this.editMenu.endDate, description: this.editMenu.description})
      })
    }
  }
  
  constructor(private builder: FormBuilder, private dialog: MatDialog, private service: MenuService,
    @Inject(MAT_DIALOG_DATA) public data: any){
  }

  menuForm = this.builder.group({
    id: this.builder.control({ value: '', disabled: true }),
    title: this.builder.control('', Validators.required),
    startDate: this.builder.control('', Validators.required),
    endDate: this.builder.control('', Validators.required),
    description: this.builder.control('', Validators.required),
  })

  ClosePopUp() {
    this.dialog.closeAll();
  }

  SaveMenu() {
    if (this.menuForm.valid) {
      const editId = this.menuForm.getRawValue().id;
      
      if (editId != '' && editId != null) {
        this.service.UpdateMenu(editId,this.data.restaurantId,this.menuForm.getRawValue()).subscribe(response => {
          this.ClosePopUp();
          alertify.success(`Menu ${this.menuForm.value.title} updated succesfully`);
        })
      } else {
        this.service.CreateMenu(this.menuForm.value,this.data.restaurantId).subscribe(response => {
            console.log(this.menuForm.value)
            console.log(this.data.restaurantId)
          this.ClosePopUp();
          alertify.success(`Menu ${this.menuForm.value.title} created succesfully`);
        })
      }

    }
  }
}
