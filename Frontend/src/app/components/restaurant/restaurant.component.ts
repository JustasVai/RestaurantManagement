import { Component, Input, OnInit, Output, ViewChild, EventEmitter } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { RestaurantModel } from 'src/app/models/RestaurantModel';
import { RestaurantService } from 'src/app/services/restaurant.service';
import { PopupComponent } from './popup/popup.component';
import * as alertify from 'alertifyjs';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
@Component({
  selector: 'app-restaurant',
  templateUrl: './restaurant.component.html',
  styleUrls: ['./restaurant.component.css']
})
export class RestaurantComponent implements OnInit {

  constructor(private dialog: MatDialog, private service: RestaurantService, private router: Router) { }

  restaurantData!: RestaurantModel[]
  finalData: any;
  isAdmin = false;
  isEmployee = false;
  @ViewChild(MatPaginator) _paginator !: MatPaginator;
  @ViewChild(MatSort) _psort !: MatSort;
  ratingArr: any[] = [];
  @Input('rating') private rating: number = 5 ;
  @Input('starCount') private starCount: number = 5;
  @Output() private ratingUpdated = new EventEmitter();

  ngOnInit(): void {
    this.LoadRestaurants();
    this.LoadRoles();
    for (let index = 0; index < this.starCount; index++) {
      this.ratingArr.push(index);
    }
    console.log(this.ratingArr)
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
      this.service.RemoveRestaurant(id).subscribe(() => {
        this.LoadRestaurants();
      })
    }, function () {

    })

  }
  
  showIcon(index: number) {
    if (this.rating >= index + 1) {
      return 'star';
    } else {
      return 'star_border';
    }
  }
  onClick(rating: number) {
    console.log(rating)
    this.ratingUpdated.emit(rating);
    return false;
  }

 
  OpenMenu(restaurantId: string) {
    this.router.navigate(['/menu', restaurantId]);
  }
  LoadRoles() {
    this.isAdmin = localStorage.getItem("isAdmin") == "true";
    this.isEmployee = localStorage.getItem("isEmployee") == "true";
    console.log(this.isEmployee == true);
  }

 

}
