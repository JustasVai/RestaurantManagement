import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UserMasterService } from 'src/app/services/user-master.service';


@Component({
  selector: 'app-modalpopup',
  templateUrl: './modalpopup.component.html',
  styleUrls: ['./modalpopup.component.css']
})
export class ModalpopupComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,private service:UserMasterService,private ref:MatDialogRef<ModalpopupComponent>){}

  selected = -1;
  roles : any;


  ngOnInit(): void {
   
    this.roles= [
      {name:"ROLE_USER",completed:true,color: 'primary'},
      {name:"ROLE_ADMIN",completed:false,color: 'accent'},
      {name:"ROLE_EMPLOYEE",completed:false,color: 'warn'}];
     this.roles.forEach((element: any) => {
        this.data.roles.forEach((role: any) => {
            if(element.name == role.roleName)
            {
             element.completed = true;
            }
        }); 
     });
  }


  AddRoleToUser(role:any){
    this.service.AddRoleToUser({username:this.data.username,roleName:role}).subscribe(item =>{
      if(item.status==200)
      {
        this.ref.close();
      }
    })
  }
 
}
