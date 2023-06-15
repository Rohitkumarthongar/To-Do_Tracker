import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { UrlsService } from 'src/app/services/urls.service';

@Component({
  selector: 'app-detailed-list',
  templateUrl: './detailed-list.component.html',
  styleUrls: ['./detailed-list.component.css']
})
export class DetailedListComponent implements OnInit {

  constructor( @Inject(MAT_DIALOG_DATA) private dialodDataDisplay: any) { }
  data: any;


  ngOnInit(): void {
    if (this.dialodDataDisplay) {
      this.data = this.dialodDataDisplay;
    }
}

}

