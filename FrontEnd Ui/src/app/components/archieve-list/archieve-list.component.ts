import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { UrlsService } from 'src/app/services/urls.service';
import { DetailedListComponent } from '../detailed-list/detailed-list.component';


@Component({
  selector: 'app-archieve-list',
  templateUrl: './archieve-list.component.html',
  styleUrls: ['./archieve-list.component.css']
})
export class ArchieveListComponent implements OnInit {

taskList:any;


searchText: string = '';
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

  constructor(private url:UrlsService, private dialog: MatDialog,private notification:NgToastService) {}

  

  ngOnInit(): void {
    this.fetchPosts();
    this.url.getAllTaskArchieve().subscribe(list=>{
      this.taskList=list;
           })
  }

  fetchPosts(): void {
    this.url.getAllTaskArchieve().subscribe(response=>
      {
        this.POSTS=response;
        console.log(response);
      }  
    )
  }

  delete(data:any)
  {
this.url.deleteArTask(data).subscribe(c=>{console.log("delete data")
this.notification.success({detail:"TASK SUCCESSFULLY DELETED",summary:'Task Permanentlt Deleted ',duration:3000});
})
this.ngOnInit();
  }


  reShift(data:any){
    console.log("ts file data"+data);
    this.url.reShiftTask(data).subscribe(x=>{  
    console.log("Data re- shiftdata");
    this.notification.success({detail:"TASK RESTORE LIST",summary:'',duration:3000});
  })
  this.ngOnInit();
  }


  openDilog(item: any) {
    this.dialog.open(DetailedListComponent, {
      data: item,
    });
    console.log(item);


}
}
