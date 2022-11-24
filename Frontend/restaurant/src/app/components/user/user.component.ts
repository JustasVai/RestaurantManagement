import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ModalpopupComponent } from 'src/app/modalpopup/modalpopup/modalpopup.component';
import { UserModel } from 'src/app/models/UserModel';
import { UserMasterService } from 'src/app/services/user-master.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  constructor(private service: UserMasterService,private dialog:MatDialog) { }

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
  Update(username:any,roles:any){
   let popup= this.dialog.open(ModalpopupComponent,{
      width:"400px",
      height:"270px",
      exitAnimationDuration:'1000ms',
      enterAnimationDuration:'1000ms',
      data:{
        username: username,
        roles: roles
      }
    });

    popup.afterClosed().subscribe(item=>{
      this.GetAllUser();
    })
  }
}

