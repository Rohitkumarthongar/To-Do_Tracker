import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { User } from 'src/app/model/User';
import { UrlsService } from 'src/app/services/urls.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
user=new User();
  hide=true;
  minDate: Date;
 maxDate: Date;


  constructor(private url:UrlsService,private router:Router,private notification:NgToastService) { 
    const currentYear = new Date().getFullYear();
    this.minDate = new Date(currentYear - 50, 0, 1);
     this.maxDate = new Date(currentYear - 4,11,31);
  }

  signUpForm = new FormGroup({
    userEmailId: new FormControl("",[Validators.required,Validators.minLength(5),Validators.maxLength(50),Validators.pattern("(^$|^.*@.*\..*$)")]),
    userName: new FormControl("",[Validators.required,Validators.minLength(5),Validators.maxLength(50)]),
    userGender: new FormControl("",[Validators.required]),
    userPassword: new FormControl("",[Validators.required,Validators.minLength(8),Validators.maxLength(30),Validators.pattern("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\w\s]).{8,}$")]),
    confirmPassword: new FormControl("",[Validators.required,Validators.minLength(8),Validators.maxLength(30)]),
    dateOfBirth: new FormControl("",[Validators.required])
  },
  [this.url.passwordMatchValidator('userPassword', 'confirmPassword')]
  );

  ngOnInit(): void {
  }

  close(){
    this.router.navigateByUrl("/");
  }

  signUp(data:any){
    this.url.authRegister(data).subscribe({ next:()=>{
      console.log("Register succesFully "+data);
      this.router.navigateByUrl('/Login');
    },
    error:()=>
            {
           console.log("Auth Register fail")
              this.router.navigateByUrl("/SignUp");
            }
            }),         
            this.url.toDoRegister(data).subscribe({ next:()=>{
              console.log("Register succesFully  To do"+data);
             this.notification.success({detail:"User SuccessFully Register",summary:"Now You can Login",duration:4000});
              this.router.navigateByUrl('/Login');
            },
                    error:()=>
                            {
                            console.log("archieve fail");
                            this.notification.error({detail:"CredentialS Are Not In Corect Form",sticky:true})
                              this.router.navigateByUrl("/SignUp");
                            }
                            })
                          }

  get userEmailId(): FormControl{
    return this.signUpForm.get("userEmailId")as FormControl
  }
  get userName(): FormControl{
    return this.signUpForm.get("userName")as FormControl
  }
  get userGender(): FormControl{
    return this.signUpForm.get("userGender")as FormControl
  }
  get userPassword(): FormControl{
    return this.signUpForm.get("userPassword")as FormControl
  }
  get dateOfBirth(): FormControl{
    return this.signUpForm.get("dateOfBirth")as FormControl
  }
}
