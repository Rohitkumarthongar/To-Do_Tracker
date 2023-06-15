import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { Task } from 'src/app/model/Task';
import { UrlsService } from 'src/app/services/urls.service';

@Component({
  selector: 'app-update-task',
  templateUrl: './update-task.component.html',
  styleUrls: ['./update-task.component.css']
})
export class UpdateTaskComponent implements OnInit {

  task: Task = new Task();
updateTaskForm!: FormGroup;

  taskId1: any;
  taskHeading1:any;
  taskCategory1:any;
  taskDescription1:any;
  taskEndDate1:any;
  taskStartDate1:any;
  status1:any;
  priority1:any;

  constructor(private url:UrlsService,private Mat:MatDialogRef<UpdateTaskComponent>,private router:Router,private notification:NgToastService) { }

ngOnInit(): void {
  this.updateTaskForm = new FormGroup({
    taskId:new FormControl(this.taskId1),
    taskHeading:new FormControl("",[Validators.required,Validators.minLength(4),Validators.maxLength(20)]), 
    taskCategory:new FormControl("",Validators.required),
    taskDescription:new FormControl("",[Validators.required,Validators.minLength(4),Validators.maxLength(200)]),
    taskEndDate:new FormControl('',Validators.required),
    taskStartDate:new FormControl('',Validators.required),
    status:new FormControl(),
    priority:new FormControl('',Validators.required)
  });
}


updateTask(data:any)
{
this.url.updateTask(data).subscribe(x=>{
  this.updateTaskForm.reset;
  this.router.navigateByUrl("/List")
  this.notification.success({detail:"Task Update SuccesFully ",duration:4000})
})
this.ngOnInit(); 
}

closeDia() {
  this.Mat.close();
}

  public get taskHeading()
{
  return this.updateTaskForm.get("taskHeading") as FormControl;
}
public get taskCategory() {
  return this.updateTaskForm.get('taskCategory') as FormControl;
}
public get taskDescription() {
  return this.updateTaskForm.get('taskDescription') as FormControl;
}
public get taskEndDate() {
  return this.updateTaskForm.get('taskEndDate') as FormControl;
}
public get taskStartDate() {
  return this.updateTaskForm.get('taskStartDate') as FormControl;
}
public get priority() {
  return this.updateTaskForm.get('priority') as FormControl;
}
public get status() {
  return this.updateTaskForm.get('status') as FormControl;
}
}
