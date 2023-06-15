import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { User } from 'src/app/model/User';
import { UrlsService } from 'src/app/services/urls.service';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent implements OnInit {
  user=new User();
  hide=true;
  minDate: Date;
 maxDate: Date;

 constructor(private url:UrlsService,private router:Router,private notification:NgToastService) { 
  const currentYear = new Date().getFullYear();
  this.minDate = new Date(currentYear - 50, 0, 1);
   this.maxDate = new Date(currentYear - 4,11,31);
}

  ngOnInit(): void {
  }

updateProfileForm = new FormGroup({
  userEmailId: new FormControl("",[Validators.required,Validators.minLength(5),Validators.maxLength(50)]),
  userName: new FormControl("",[Validators.required,Validators.minLength(5),Validators.maxLength(50)]),
  userGender: new FormControl("",[Validators.required]),
  userPassword: new FormControl("",[Validators.required,Validators.minLength(8),Validators.maxLength(30)]),
  confirmPassword: new FormControl("",[Validators.required,Validators.minLength(8),Validators.maxLength(30)]),
  dateOfBirth: new FormControl("",[Validators.required])
},
[this.url.passwordMatchValidator('userPassword', 'confirmPassword')]
);

  updateUser(data:any){
    console.log(data.value);
    this.url.updateUser(data).subscribe({ next:()=>{
                    console.log("Update Auth User");
                    this.updateProfileForm.reset();
                    
                    this.router.navigateByUrl("/Home");
                    
                  },
                  error:()=>
                          {
                            console.log("Sorry Something Went Wrong........");
                            this.router.navigateByUrl("/updateProfile");
                          }
                          }),
                          this.url.updateUserProfile(data).subscribe({ next:()=>{
                            console.log("Update To Do Service User");
                            this.updateProfileForm.reset();
          
                            this.router.navigateByUrl("/Home");
                            this.notification.success({detail:"User Profile Update succesFully",duration:4000})
                            
                          },
                                  error:()=>
                                          {
                                           this.notification.error({detail:"Credentials Are Not Correct",summary:"Check the Credentials",duration:4000})
                                            this.router.navigateByUrl("/updateProfile");
                                          }
                                          })
                                        }                      

close()
{
  this.router.navigateByUrl("/Home");
}

    get userEmailId(): FormControl{
      return this.updateProfileForm.get("userEmailId")as FormControl
    }
    get userName(): FormControl{
      return this.updateProfileForm.get("userName")as FormControl
    }
    get userGender(): FormControl{
      return this.updateProfileForm.get("userGender")as FormControl
    }
    get userPassword(): FormControl{
      return this.updateProfileForm.get("userPassword")as FormControl
    }
    get dateOfBirth(): FormControl{
      return this.updateProfileForm.get("dateOfBirth")as FormControl
    }
}
