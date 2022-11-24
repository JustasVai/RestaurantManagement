import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { RestaurantModel } from 'src/app/models/RestaurantModel';
import { RestaurantService } from 'src/app/services/restaurant.service';
import { PopupComponent } from './popup/popup.component';
import * as alertify from 'alertifyjs';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
@Component({
  selector: 'app-restaurant',
  templateUrl: './restaurant.component.html',
  styleUrls: ['./restaurant.component.css']
})
export class RestaurantComponent implements OnInit {

  constructor(private dialog: MatDialog, private service: RestaurantService) { }

  restaurantData!: RestaurantModel[]
  finalData : any;

  @ViewChild(MatPaginator) _paginator !: MatPaginator;
  @ViewChild(MatSort) _psort !: MatSort;
  
  ngOnInit(): void {
    this.LoadRestaurants();
  }

  displayColumns: string[] = ["name", "address", "rating", "phoneNumber", "description", "action"]

  OpenPopUp(id: any, rating: boolean) {
    const _popup = this.dialog.open(PopupComponent, {
      width: '500px',
      exitAnimationDuration: '1000ms',
      enterAnimationDuration: '1000ms',
      data: {
        id: id,
        rating: rating
      }
    })
    _popup.afterClosed().subscribe(r => {
      this.LoadRestaurants();
    })
  }

  LoadRestaurants() {
    this.service.GetAllRestaurants().subscribe(response => {
      this.restaurantData = response;
      this.finalData = new MatTableDataSource<RestaurantModel>(this.restaurantData);
      this.finalData.paginator = this._paginator;
      this.finalData.sort = this._psort;
    })
  }

  EditRestaurant(id: any, rating: boolean) {
    this.OpenPopUp(id, rating);
  }
  RemoveRestaurant(id: any) {
    alertify.confirm("Remove Restaurant ", `Are you sure you want to remove restaurant with id = ${id}?`, () => {
      this.service.RemoveRestaurant(id).subscribe(response => {
        this.LoadRestaurants();
      })
    }, function () {

    })

  }
}
