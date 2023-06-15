export class Task {
    taskId:number;
taskHeading :string;
taskCategory:string;
taskDescription:string;
taskEndDate:Date;
taskStartDate:Date;
status:boolean;
priority:string;
  value: any;
constructor(){
  this.taskId=0;
  this.taskHeading="";
  this.taskCategory="";
  this.taskDescription="";
  this.taskEndDate=new Date();
  this.taskStartDate=new Date();
  this.status=false;
  this.priority="";
}
}

