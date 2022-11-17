import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { UserModel } from 'src/app/models/UserModel';
import { UserMasterService } from 'src/app/services/user-master.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  constructor(private service: UserMasterService) { }

  ngOnInit(): void {
    this.GetAllUser();
  }
  @ViewChild(MatPaginator) paginator !: MatPaginator;

  UserDetail: any;
  dataSource: any;

  GetAllUser() {
    this.service.GetAllUser().subscribe((res) => {
      this.UserDetail = res;
      this.dataSource = new MatTableDataSource<UserModel>(this.UserDetail);
      this.dataSource.paginator= this.paginator;
    })
  }

  displayedColumns: string[] = ['username', 'email', 'roles', 'Action'];
  //dataSource = ELEMENT_DATA;
}
