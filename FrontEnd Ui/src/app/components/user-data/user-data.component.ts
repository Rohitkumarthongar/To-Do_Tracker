import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { UrlsService } from 'src/app/services/urls.service';

@Component({
  selector: 'app-user-data',
  templateUrl: './user-data.component.html',
  styleUrls: ['./user-data.component.css']
})
export class UserDataComponent implements OnInit {

  constructor(private url:UrlsService,private notification:NgToastService,private router:Router){ }
data:any;
  ngOnInit(): void {
     this.getUser();
  }
  

    getUser()
    {
      this.url.getUserDetails().subscribe(x=>
        {
          console.log(x)
          this.data=x;
          console.log("user Details");
          this.notification.success({detail:"User SuccesFully Log Out",duration:3000});
        })
    }

    logOut() {
      this.url.LoginStatus =false;
      this.notification.success({detail:"User SuccesFully Log Out",duration:3000});
      this.ngOnInit();
      this.router.navigateByUrl('/');
    }

}
