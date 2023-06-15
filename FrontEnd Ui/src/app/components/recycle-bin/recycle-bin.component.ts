import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { UrlsService } from 'src/app/services/urls.service';
import { DetailedListComponent } from '../detailed-list/detailed-list.component';

@Component({
  selector: 'app-recycle-bin',
  templateUrl: './recycle-bin.component.html',
  styleUrls: ['./recycle-bin.component.css']
})
export class RecycleBinComponent implements OnInit {
  searchText: string = '';
  taskList:any;
  POSTS:any;
  p: number = 1;
  count :number = 0;
  tableSize: number = 8;
  tableSizes: any = [3, 6, 9, 12];
  onTableDataChange(event: any) {
    this.p = event;
  }
  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.p = 1;
  }
  constructor(private url:UrlsService, private dialog: MatDialog,private notification:NgToastService,private router:Router) { }


  ngOnInit(): void {
    this.fetchPosts();
    this.url.getDeletedTask().subscribe(list=>{
      this.taskList=list;
           })
  }

  fetchPosts(): void {
    this.url.getCompletedTask().subscribe(response=>
      {
        this.POSTS=response;
        console.log(response);
      }  
    )
  }

  delete(data:any) {
    this.url.deleteDlTask(data).subscribe((d:any) => { console.log("recycle bin list");
    this.notification.success({detail:"Task Deleted",summary:"Task Deleted Permanently",duration:3000})});
    this.ngOnInit(); 
  }

  Restore(data:any)
  {
    this.url.restoreTask(data).subscribe((d:any) => { console.log("Data Restored");
    this.notification.success({detail:"Task Restore to List",duration:3000}) });
    this.ngOnInit(); 
  }

  openDilog(item: any) {
    this.dialog.open(DetailedListComponent, {
      data: item,
    });
    console.log(item);
}
}
