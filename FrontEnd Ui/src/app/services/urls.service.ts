import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ValidatorFn, AbstractControl } from '@angular/forms';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UrlsService {

  isLogged:boolean=false;
    redirectUrl:String='';
    loggedIn: boolean=false;
    userEmailId!: string;
    token: any;
    data:any;
    demo:any;
    TaskId:any;
    userName:any;
    status:boolean=false;

  userLogin()
  {
    this.isLogged=true;
  }


set LoginStatus(data: boolean) {
  this.loggedIn = data;
}
get LoginStatus(): boolean {
  return this.loggedIn;
}



setStatus(data: boolean) {
  this.status = data;
}
getStatus(): boolean {
  return this.status;
}


  setUseEmailId(data: string) {
    this.userEmailId = data;
  }
  getUseEmailId(): string {
    return this.userEmailId;
  }

  setTaskId(data: string) {
    this.TaskId = data;
  }
  getTaskId(): string {
  
    return this.TaskId;
  }

 
  setToken(tok: any) {
    this.token = tok;
  }
  getTokenWithHeader() {
    let completedToken='Bearer '+this.token.token;
    const header=new HttpHeaders().set("Authorization",completedToken);
    return header;
  }



  constructor(private httpclient: HttpClient) { }

// *********************Auth Service***************************
  authRegister(user:any){
    return this.httpclient.post("http://localhost:8000/api/to-do/auth/registerUser",user,{responseType:'text' as 'json'})
  }

  archieveRegister(user:any)
  {
    return this.httpclient.post("http://localhost:8000/api/to-do/archieveService/registerUser",user,{responseType:'text' as 'json'});
  }
  toDoRegister(user:any)
  {
    return this.httpclient.post("http://localhost:8000/api/to-do/toDoService/registerUser",user,{responseType:'text' as 'json'});
  }

  loginUser(user: any) {
    this.demo=user.userEmailId;
    return  this.httpclient.post("http://localhost:8000/api/to-do/auth/loginUser",user,{responseType:'text' as 'json'})
  }

  updateUser(user:any)
  {
    return this.httpclient.put(`${"http://localhost:8000/api/to-do/auth/updateProfile"}/${user.userEmailId}`,user,{headers:this.getTokenWithHeader()});
  }
  updateUserProfile(user:any)
  {
   return this.httpclient.put(`${"http://localhost:8000/api/to-do/toDoService/updateProfile"}/${user.userEmailId}`,user,{headers:this.getTokenWithHeader()});
 }

 updateArchieveUserProfile(user:any)
 {
  return this.httpclient.put(`${"http://localhost:8000/api/to-do/archieveService/updateProfile"}/${user.userEmailId}`,user,{headers:this.getTokenWithHeader()});
}

// **********************************To Do Service************************************

resetAuthPasswords(user:any)
{
 
return this.httpclient.get<any>(`${"http://localhost:8000/api/to-do/auth/resetPassword"}/${user.userEmailId}`,user);
}

resetServicePasswords(user:any)
{
  return this.httpclient.get<any>(`${"http://localhost:8000/api/to-do/toDoService/resetPassword"}/${user.userEmailId}/${this.userName}`,user);
}

resetArchievePasswords(user:any)
{
  this.userName=user.userName;
  return this.httpclient.put<any>(`${"http://localhost:8000/api/to-do/archieveService/resetPassword"}/${user.userEmailId}/${this.userName}`,user);
}


low(){return this.httpclient.get(`${"http://localhost:8000/api/to-do/toDoService/lowPriority"}/${this.demo}`,{headers:this.getTokenWithHeader()})}
high(){return this.httpclient.get(`${"http://localhost:8000/api/to-do/toDoService/highPriority"}/${this.demo}`,{headers:this.getTokenWithHeader()})}

getUserDetails()

{console.log(this.demo);
  console.log("Url Service Is Working");
  console.log(this.httpclient.get<any>(`${"http://localhost:8000/api/to-do/toDoService/getUserDetails"}/${this.demo}`).pipe(map((res:any)=>
  {
    return res;
  })));
  
return this.httpclient.get<any>(`${"http://localhost:8000/api/to-do/toDoService/getUserDetails"}/${this.demo}`).pipe(map((res:any)=>
{
  return res;
}));
}



  addTaskArchieve(task: any) 
  {
    return this.httpclient.post<any>(`${"http://localhost:8000/api/to-do/archieveService/addTask"}/${this.demo}`,task,{headers:this.getTokenWithHeader()})
  }

  addTaskToDoList(task: any) {
    return this.httpclient.post<any>(`${"http://localhost:8000/api/to-do/toDoService/addTask"}/${this.demo}`,task,{headers:this.getTokenWithHeader()})
  }

  getAllTask(): Observable<any> {
    return this.httpclient.get<any>(`${"http://localhost:8000/api/to-do/toDoService/allTask"}/${this.demo}`,{headers:this.getTokenWithHeader()}).pipe(map((res:any)=>
    {
      return res;
    }));
  }

  getAllTaskArchieve(): Observable<any> {
    return this.httpclient.get<any>(`${"http://localhost:8000/api/to-do/toDoService/allArTask"}/${this.demo}`,{headers:this.getTokenWithHeader()});
  }

 
  getCompletedTask(): Observable<any> {
    return this.httpclient.get<any>(`${"http://localhost:8000/api/to-do/toDoService/CompletedTask"}/${this.demo}`,{headers:this.getTokenWithHeader()}).pipe(map((res:any)=>
    {
      return res;
    }));
  }

  getTasksOverDue() {
    return this.httpclient.get<any>(`${"http://localhost:8000/api/to-do/toDoService/taskOverDue"}/${this.demo}`,{headers:this.getTokenWithHeader()}).pipe(map((res:any)=>
    {
      return res;
    }));
  }

  getTasksWithNearDueDate() {
    return this.httpclient.get<any>(`${"http://localhost:8000/api/to-do/toDoService/taskNearDue"}/${this.demo}`,{headers:this.getTokenWithHeader()}).pipe(map((res:any)=>
    {
      return res;
    }));
  }

  getTasksToday() {
    return this.httpclient.get<any>(`${"http://localhost:8000/api/to-do/toDoService/todayTask"}/${this.demo}`,{headers:this.getTokenWithHeader()}).pipe(map((res:any)=>
    {
      return res;
    }));
  }

  updateTask(data:any){
    console.log(data);
    return this.httpclient.put(`${"http://localhost:8000/api/to-do/toDoService/updateTask"}/${this.demo}`,data,{headers:this.getTokenWithHeader()});
  }


  updateTaskPriority(task:any){
    return this.httpclient.put<any>(`${"http://localhost:8000/api/to-do/toDoService/updateTaskPriority"}/${this.demo}`, task,{headers:this.getTokenWithHeader()});
  }
  updateTaskHeading(task:any){
    return this.httpclient.put<any>(`${"http://localhost:8000/api/to-do/toDoService/updateTaskHeading"}/${this.demo}`, task,{headers:this.getTokenWithHeader()});
  }

  updateTaskStartDate(task:any){
    return this.httpclient.put<any>(`${"http://localhost:8000/api/to-do/toDoService/updateTaskStartDate"}/${this.demo}`, task,{headers:this.getTokenWithHeader()});
  }

  updateTaskEndDate(task:any){
    return this.httpclient.put<any>(`${"http://localhost:8000/api/to-do/toDoService/updateTaskEndDate"}/${this.demo}`, task,{headers:this.getTokenWithHeader()});
  }

  CompleteTask(data:any)
  {
    return this.httpclient.put<any>(`${"http://localhost:8000/api/to-do/toDoService/CompleteTask"}/${this.demo}/${data}`,{headers:this.getTokenWithHeader()})  
  }


shiftTask(data:any)
{
  console.log(data+"----202")
  console.log(this.taskId+" 203")
  return this.httpclient.put<any>(`${"http://localhost:8000/api/to-do/toDoService/shift"}/${this.demo}/${data}`,{headers:this.getTokenWithHeader()}) 
}

reShiftTask(data:any)
{
  console.log(data+"----209")
  console.log(this.taskId+" 203")
  return this.httpclient.put<any>(`${"http://localhost:8000/api/to-do/toDoService/reShift"}/${this.demo}/${data}`,{headers:this.getTokenWithHeader()}) 
}
removeTask(data:any)
{
  return this.httpclient.put<any>(`${"http://localhost:8000/api/to-do/toDoService/remove"}/${this.demo}/${data}`,{headers:this.getTokenWithHeader()}) 
}

restoreTask(data:any)
{
  return this.httpclient.put<any>(`${"http://localhost:8000/api/to-do/toDoService/restore"}/${this.demo}/${data}`,{headers:this.getTokenWithHeader()}) 
}

taskId:any;


deleteDlTask(data:any)
{
  this.TaskId=data;
  console.log(this.taskId)
  return this.httpclient.delete<any>(`${"http://localhost:8000/api/to-do/toDoService/deleteDl"}/${this.demo}/${this.TaskId}`,data) 
}

deleteArTask(data:any)
{
  this.TaskId=data;
  console.log(this.taskId)
  return this.httpclient.delete<any>(`${"http://localhost:8000/api/to-do/toDoService/deleteAr"}/${this.demo}/${this.TaskId}`,data) 
}

getDeletedTask(): Observable<any> {
  return this.httpclient.get<any>(`${"http://localhost:8000/api/to-do/toDoService/allDeletedTask"}/${this.demo}`,{headers:this.getTokenWithHeader()}).pipe(map((res:any)=>
  {
    return res;
  }));
}

getPendingTask(): Observable<any> {
  return this.httpclient.get<any>(`${"http://localhost:8000/api/to-do/toDoService/allPendingTask"}/${this.demo}`,{headers:this.getTokenWithHeader()}).pipe(map((res:any)=>
  {
    return res;
  }));
}

feedback(data:any)
{console.log(data);
  console.log("emaill id"+ data.userEmailId);
  

  return this.httpclient.post<any>(`${"http://localhost:8000/api/to-do/auth/feedback"}/${data.userEmailId}/${data.message}`,data) ;
 
}

// getPendingToDoTask()
// {
//   return this.httpclient.get(`${"http://localhost:8000/api/to-do/toDoService/allPendingTask"}/${this.demo}`).pipe(map((res:any)=>
//   {
//     return res;
//   }));
// }

// getRecycleBin()
// {
// return this.httpclient.get(`${"http://localhost:8000/api/to-do/toDoService/deletedTask"}/${this.demo}`).pipe(map((res:any)=>
// {
//   return res;
// }));
// }



passwordMatchValidator(controlName: string, checkControlName: string): ValidatorFn {
  return (controls: AbstractControl) => {
    const control = controls.get(controlName);
    const checkControl = controls.get(checkControlName);
    if (checkControl?.errors && !checkControl?.errors["matching"]) {
      return null;
    }
    if (control?.value !== checkControl?.value) {
      controls?.get(checkControlName)?.setErrors({ passmatching: true });
      return { matching: true };
    } else {
      return null;
    }
  };    
}

}
