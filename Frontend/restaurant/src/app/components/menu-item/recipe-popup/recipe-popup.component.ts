import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-recipe-popup',
  templateUrl: './recipe-popup.component.html',
  styleUrls: ['./recipe-popup.component.css']
})
export class RecipePopupComponent implements OnInit {

  recipe: any;

  ngOnInit(): void {
    this.recipe = this.data.recipe;
  }
  constructor(private dialog: MatDialog, @Inject(MAT_DIALOG_DATA) public data: any) {
  }
  
}
