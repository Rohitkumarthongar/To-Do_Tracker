import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { UrlsService } from 'src/app/services/urls.service';
import { DetailedListComponent } from '../detailed-list/detailed-list.component';
import { UpdateTaskComponent } from '../update-task/update-task.component';

@Component({
  selector: 'app-low-priority',
  templateUrl: './low-priority.component.html',
  styleUrls: ['./low-priority.component.css']
})
export class LowPriorityComponent implements OnInit {
  public List:any;
  taskList:any;
  searchKey:any="";
  searchText: string = '';
  
    taskId1:any;
    taskHeading1:any;
    taskCategory1:any;
    taskDescription1:any;
    taskEndDate1:any;
    taskStartDate1:any;
    status1:any;
    priority1:any;

    POSTS:any;
  p: number = 1;
  count :number = 0;
  tableSize: number = 5;
  tableSizes: any = [3, 6, 9, 12];
  onTableDataChange(event: any) {
    this.p = event;
  }
  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.p = 1;
  }
  
  @Input() datareceive: any;
  @Input() typea: any;
  
    constructor(private url:UrlsService, private dialog: MatDialog,private mat:MatDialog,private router:Router,private alert:NgToastService) {}
  
    ngOnInit(): void {
      this.fetchPosts();
      this.url.low().subscribe(list=>{
        this.taskList=list;
             })
    }

    

    fetchPosts(): void {
      this.url.low().subscribe(response=>
        {
          this.POSTS=response;
          console.log(response);
        }  
      )
    }

  
  
  remove(data:any){
    console.log(data);
    this.url.removeTask(data).subscribe(x=>{
    this.alert.success({detail:"Task Delete",summary:"Task Shifted to Recycle Bin",duration:2000})  
  setTimeout(()=>this.ngOnInit(),2000)
  })  }
  
  shiftTask(data:any){
    console.log("ts file data"+data);
    this.url.shiftTask(data).subscribe(x=>{
    this.router.navigateByUrl("/List");
    this.alert.success({detail:"Task save to Archieve",duration:2000})  
  }) 
  setTimeout(()=>this.ngOnInit(),2000)
  }
  
    
    f2(taskId:any,taskHeading:any,taskCategory:any,
      taskDescription:any,taskEndDate:any,taskStartDate:any,status:any,priority:any){
        let dialog=this.mat.open(UpdateTaskComponent,{height:"70%",width:"650px"})
        dialog.componentInstance.taskId1=taskId;
        dialog.componentInstance.taskHeading1=taskHeading;
        dialog.componentInstance.taskCategory1=taskCategory;
        dialog.componentInstance.taskDescription1=taskDescription;
        dialog.componentInstance.taskEndDate1=taskEndDate;
        dialog.componentInstance.taskStartDate1=taskStartDate;
        dialog.componentInstance.status1=status;
        dialog.componentInstance.priority1=priority;
        dialog.afterClosed().subscribe(x=>{
          setTimeout(()=>this.ngOnInit(),1000)
        })
    }
  
    key:string='id';
    reverse:boolean=false;
    sort(key:any){
      this.key=key;
      this.reverse=!this.reverse;
      console.log(key);
    }
  
  
    openDilog(item: any) {
      this.dialog.open(DetailedListComponent, {
        data: item,
      });
      console.log(item);
  }

}
