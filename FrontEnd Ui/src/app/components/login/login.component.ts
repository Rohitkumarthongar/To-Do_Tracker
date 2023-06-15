import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UrlsService } from 'src/app/services/urls.service';
import {User } from 'src/app/model/User';
import { MatSnackBar } from '@angular/material/snack-bar';
import { RoDoGuardGuard } from 'src/app/services/ro-do-guard.guard';
import { NgToastService } from 'ng-angular-popup';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user=new User();
  hide=true;
  constructor(private router:Router,private url:UrlsService,private alert:MatSnackBar,private auth:RoDoGuardGuard,private notification:NgToastService) { }

  loginForm = new FormGroup({
    userEmailId: new FormControl("",[Validators.required,Validators.minLength(5),Validators.maxLength(50),Validators.pattern("(^$|^.*@.*\..*$)")]),
    userPassword: new FormControl("",[Validators.required,Validators.minLength(8),Validators.maxLength(20),Validators.pattern("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\w\s]).{8,}$")]),
  });
  
  get userPassword(): FormControl{
    return this.loginForm.get("userPassword")as FormControl
  }
  get userEmailId(): FormControl{
    return this.loginForm.get("userEmailId")as FormControl
  }

  ngOnInit(): void {
    
  }

  close()
  {
    this.router.navigateByUrl("/");  
  }

login(data:any){
  this.url.loginUser(data).subscribe({
    next:(x)=>
    {
      console.log(x);
        console.log(data);
        this.url.setUseEmailId(this.user.userEmailId);
        this.url.setToken(x);
        this.url.LoginStatus = data;
        this.notification.success({detail:"SUCCESSFULLY LOGIN",summary:'Login With '+data.userEmailId,duration:5000});
              this.router.navigateByUrl("/Home")
    },
    error:()=>
    {
      this.notification.error({detail:"ERROR",summary:'Credentials Are Not Matched',sticky:true});
      this.router.navigateByUrl("/SignUp")
    }
    });
    this.loginForm.reset();
  }
 }

