import { Component, OnInit } from '@angular/core';
import { UrlsService } from 'src/app/services/urls.service';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent implements OnInit {

  dueDate: any;
  NearDate: any;
  today: any;

  constructor(private url:UrlsService) {}

  

  ngOnInit(): void { 
    this.url.getTasksOverDue().subscribe(x=>{
      console.log("OverDue DAte");
      this.dueDate=x;});
    this.url.getTasksWithNearDueDate().subscribe(x=>{
      console.log("Near Due DAte");
      this.NearDate=x;});
 this.url.getTasksToday().subscribe(x=>{
  console.log("Today DAte");
  this.today=x;});
  }

  OverDueDate(){this.url.getTasksOverDue().subscribe(x=>{ this.dueDate=x;})};

NearDueDate(){this.url.getTasksWithNearDueDate().subscribe(x=>{this.NearDate=x;
  })}

  todayTask(){this.url.getTasksToday().subscribe(x=>{this.today=x;})}

}
