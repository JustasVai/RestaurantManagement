import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import * as e from 'Node_modules/@types/cors';
import { RestaurantService } from 'src/app/services/restaurant.service';
import * as alertify from 'alertifyjs'
@Component({
  selector: 'app-popup',
  templateUrl: './popup.component.html',
  styleUrls: ['./popup.component.css']
})
export class PopupComponent implements OnInit {
  editRestaurant: any;

  constructor(private builder: FormBuilder, private dialog: MatDialog, private service: RestaurantService,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
    if (this.data.id != '' && this.data.id != null) {
      this.service.GetRestaurantById(this.data.id).subscribe(response => {
        this.editRestaurant = response;
        this.restaurantForm.setValue({ id: this.editRestaurant.id, name: this.editRestaurant.name, address: this.editRestaurant.address, rating: this.editRestaurant.rating, phoneNumber: this.editRestaurant.phoneNumber, description: this.editRestaurant.description })
      })
    }
  }

  restaurantForm = this.builder.group({
    id: this.builder.control({ value: '', disabled: true }),
    name: this.builder.control('', Validators.required),
    address: this.builder.control('', Validators.required),
    rating: this.builder.control({ value: 0, disabled: !this.data.rating}),
    phoneNumber: this.builder.control('', Validators.required),
    description: this.builder.control('', Validators.required),
  })

  SaveRestaurant() {
    if (this.restaurantForm.valid) {
      const editId = this.restaurantForm.getRawValue().id;
      if (editId != '' && editId != null) {
        this.service.UpdateRestaurant(editId,this.restaurantForm.getRawValue()).subscribe(response => {
          this.ClosePopUp();
          alertify.success(`Restaurant ${this.restaurantForm.value.name} updated succesfully`);
        })
      } else {
        this.service.CreateRestaurant(this.restaurantForm.value).subscribe(response => {
          this.ClosePopUp();
          alertify.success(`Restaurant ${this.restaurantForm.value.name} created succesfully`);
        })
      }

    }
  }

  ClosePopUp() {
    this.dialog.closeAll();
  }
}
