import { Component, TemplateRef, ViewChild} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { UserDataComponent } from './components/user-data/user-data.component';
import { UrlsService } from './services/urls.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'To-Do';
  opened:any;
  Name: string="";
  today: number = Date.now();
  userData: any;

  constructor(private url:UrlsService,  private dialog: MatDialog,private router:Router,private alert:NgToastService,private profile:MatDialog,){}
  ngOnInit(): void { }

  login(){return this.url.loggedIn;}

// Dis changes here
  


  getUserDetails()
  {
    this.url.getUserDetails().subscribe(x=>{
          this.userData=x;
          console.log("USer Details");
          
          console.log(this.userData);
          
          this.Name=this.userData.userName;
          console.log("User details");
        })
  }

  // openUpdateProfile() {
  //   this.dialog.open(UpdateProfileComponent, {
  //   }).afterClosed().subscribe(val => {
  //    this.router.navigateByUrl("/Home")
  //   });}
  

  // openDilog(item: any) {
  //   this.dialog.open(DetailedListComponent, {
  //     data: item,
  //   }).afterClosed().subscribe(val => {
  //     this.router.navigateByUrl("/List");
  //   });}
}
