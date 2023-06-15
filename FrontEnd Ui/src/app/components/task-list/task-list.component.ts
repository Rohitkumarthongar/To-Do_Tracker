import { Component, Input, OnInit, TemplateRef } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { UrlsService } from 'src/app/services/urls.service';
import { DetailedListComponent } from '../detailed-list/detailed-list.component';
import { UpdateTaskComponent } from '../update-task/update-task.component';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {
 
  public List:any;
taskList:any;
priority:string='';
searchKey:any="";
searchText: string = '';
key:string='id';
reverse:boolean=false;

  taskId1:any;
  taskHeading1:any;
  taskCategory1:any;
  taskDescription1:any;
  taskEndDate1:any;
  taskStartDate1:any;
  status1:any;
  priority1:any;

@Input() datareceive: any;
@Input() typea: any;



  constructor(private url:UrlsService, private dialog: MatDialog,private mat:MatDialog,private router:Router,private notification:NgToastService) {}

  ngOnInit(): void {
    this.getLowPriority();
    this.fetchPosts();
    this.url.getAllTask().subscribe(list=>{
      this.taskList=list;
           })
  }

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

  fetchPosts(): void {
    this.url.getAllTaskArchieve().subscribe(response=>
      {
        this.POSTS=response;
        console.log(response);
      }  
    )
  }

  info(info: TemplateRef<any>) {
    this.dialog.open(info);
  }

  getLowPriority()
  {
    this.url.low().subscribe(response=>
      {
        this.POSTS=response;
        console.log(response);
      }  
    )
  }
  
  getAllTask() {
    this.url.getAllTask().subscribe(d => { this.datareceive = d; });
  }

remove(data:any){
  console.log(data);
  this.url.removeTask(data).subscribe(x=>{
 this.notification.success({detail:"Task Deleted",summary:"Removed to Trash",duration:4000});})
this.ngOnInit();
}

shiftTask(data:any){
  console.log("ts file data"+data);
  this.url.shiftTask(data).subscribe(x=>{
 this.notification.success({detail:"Task SuccesFully Archieved",summary:"Task Saved To Archieve List",duration:4000});})
 this.ngOnInit();
}

  
  f2(taskId:any,taskHeading:any,taskCategory:any,
    taskDescription:any,taskEndData:any,taskStartDate:any,status:any,priority:any){
      let dialog=this.mat.open(UpdateTaskComponent)
      dialog.componentInstance.taskId1=taskId;
      dialog.componentInstance.taskHeading1=taskHeading;
      dialog.componentInstance.taskCategory1=taskCategory;
      dialog.componentInstance.taskDescription1=taskDescription;
      dialog.componentInstance.taskEndDate1=taskEndData;
      dialog.componentInstance.taskStartDate1=taskStartDate;
      dialog.componentInstance.status1=status;
      dialog.componentInstance.priority1=priority;
      dialog.afterClosed().subscribe(x=>{
        this.ngOnInit();
      })
  }

  sort(key:any){
    this.key=key;
    this.reverse=!this.reverse;
    console.log(key);
  }


  openDilog(item: any) {
    this.dialog.open(DetailedListComponent, {
      data: item,
    }).afterClosed().subscribe(val => {
      this.router.navigateByUrl("/List");
    });
}

statusChange(data:any)
{
  this.url.CompleteTask(data).subscribe(x=>{
    console.log("Working");
    setTimeout(()=>this.ngOnInit(),1000)
  })
}
}