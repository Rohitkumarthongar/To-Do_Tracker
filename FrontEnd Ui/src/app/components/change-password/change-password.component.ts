import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { User } from 'src/app/model/User';
import { UrlsService } from 'src/app/services/urls.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  user=new User();
  constructor(private url:UrlsService,private router:Router,private notification:NgToastService) { }

  ngOnInit(): void {
  }
  hide=false;
  resetPasswordForm=new FormGroup(
    {
      userEmailId: new FormControl("",[Validators.required,Validators.minLength(5),Validators.maxLength(50),Validators.pattern("(^$|^.*@.*\..*$)")]),
      userName: new FormControl("",[Validators.required,Validators.minLength(5),Validators.maxLength(50)]),
  
    },
    [this.url.passwordMatchValidator('userPassword', 'confirmPassword')]
    );

    get userEmailId(): FormControl{
      return this.resetPasswordForm.get("userEmailId")as FormControl
    }
    get userName(): FormControl{
      return this.resetPasswordForm.get("userName")as FormControl
    }

  changepassword(data:any){
    this.url.resetAuthPasswords(data).subscribe(
      (d:any)=>{console.log(d);
        console.log("password reset auth")
        this.router.navigateByUrl("/Login")
      },
      // (e:any)=>{console.log(e.error.message);
      // }
    );
    // this.url.resetServicePasswords(data).subscribe(
    //   (d)=>{console.log(d);
    //     this.notification.success({detail:"Password Reset Succesfully",duration:4000})
    //     this.router.navigateByUrl("/Login")
    //   },
    //   (e)=>{
    //    this.notification.error({detail:"Oops! Data Not Matched",duration:4000})
    //     this.router.navigateByUrl("/forgotPassword")
    //   }
    // );
  }

  cancel(){
    this.router.navigateByUrl("/Login");
  }
}
