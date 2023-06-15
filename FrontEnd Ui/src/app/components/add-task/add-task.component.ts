import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, ValidatorFn } from '@angular/forms';
import { UrlsService } from 'src/app/services/urls.service';
import { Task } from 'src/app/model/Task';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NgToastService } from 'ng-angular-popup';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent implements OnInit {

  task: Task = new Task();
  addTaskToList!: FormGroup;
  constructor(private url:UrlsService, private router:Router,private alert:MatSnackBar,private notification:NgToastService) { }


  ngOnInit(): void {
    this.addTaskToList= new FormGroup({
      // taskId:new FormControl('',Validators.required),
      taskHeading:new FormControl("",[Validators.required,Validators.minLength(4),Validators.maxLength(20)]), 
      taskCategory:new FormControl("",Validators.required),
      taskDescription:new FormControl("",[Validators.required,Validators.minLength(4),Validators.maxLength(200)]),
      taskEndDate: new FormControl(null, [Validators.required,this.dateRangeValidator]),
      taskStartDate: new FormControl(null, [Validators.required,this.stratDate]),
      status:new FormControl("false"),
      priority:new FormControl("LOW",Validators.required)
    });
  }



   stratDate(control: FormControl): { [s: string]: boolean } |null {
    const fromDate = control.value;
    if (!fromDate) {
      return null;
    }
    const fromDateTime = new Date(fromDate);
    const currentDateTime = new Date();
    if (fromDateTime.setHours(0,0,0,0) > currentDateTime.setHours(0,0,0,0) || fromDateTime.setHours(0,0,0,0) === currentDateTime.setHours(0,0,0,0)) {
      return null;
    }
    return { notvalid: true }
  }

 
  public get taskHeading()
  {
    return this.addTaskToList.get("taskHeading") as FormControl;
  }
  public get taskCategory() {
    return this.addTaskToList.get('taskCategory') as FormControl;
  }
  public get taskDescription() {
    return this.addTaskToList.get('taskDescription') as FormControl;
  }
  public get taskEndDate() {
    return this.addTaskToList.get('taskEndDate') as FormControl;
  }
  public get taskStartDate() {
    return this.addTaskToList.get('taskStartDate') as FormControl;
  }
  public get priority() {
    return this.addTaskToList.get('priority') as FormControl;
  }
  public get status() {
    return this.addTaskToList.get('status') as FormControl;
  }
  dateRangeValidator: ValidatorFn = (): {
    [key: string]: any;
  } | null => {
    let invalid = false;
    const from = this.addTaskToList && this.addTaskToList.get("taskStartDate")?.value;
    const to = this.addTaskToList && this.addTaskToList.get("taskEndDate")?.value;
    if (from && to) {
      invalid = new Date(from).valueOf() >= new Date(to).valueOf();
    }
    return invalid ? { invalidRange: { from, to } } : null;
  };

  close()
  {
    this.router.navigateByUrl("/Home");
    this.notification.info({detail:"Add task Template closed ",summary:'',duration:3000});
  }
  
 addTask() {
  console.log()
  this.url.addTaskToDoList(this.addTaskToList.value).subscribe(x=>{
    this.addTaskToList.reset();
    this.notification.success({detail:"Task Succesfully Added",summary:' ',duration:5000});
    this.router.navigateByUrl("/Home")
})
                  
    }

  addTaskArchieve(data:any){
    console.log(data)
    this.url.addTaskArchieve(data).subscribe(x=>{  this.addTaskToList.reset();
      this.notification.success({detail:"Task Succesfully Added",summary:' ',duration:5000});
      this.router.navigateByUrl("/Home")})
          
      }

  }
