import { Component, OnInit } from '@angular/core';
import { UrlsService } from 'src/app/services/urls.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
opened=false;
today: number = Date.now();
userName:string="";

  constructor(private url:UrlsService) { }
  ngOnInit(): void {
  //  this.getUserName();
  }

  // getUserName()
  // {
  //   this.url.updateUserProfile().subscribe(x=>{
  //     console.log("User Details")
  //   })
  // }

}
