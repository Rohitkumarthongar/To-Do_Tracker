import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { ToastrService } from 'ngx-toastr';
import { UrlsService } from '../services/urls.service';

@Component({
  selector: 'app-feed-back',
  templateUrl: './feed-back.component.html',
  styleUrls: ['./feed-back.component.css']
})
export class FeedBackComponent implements OnInit {

  constructor(private url:UrlsService,private notification:NgToastService,private router:Router) { }
  ngOnInit(): void {
  }

  feedBackForm = new FormGroup({
     userEmailId: new FormControl("",[Validators.required,Validators.minLength(5),Validators.maxLength(50),Validators.pattern("(^$|^.*@.*\..*$)")]),
message:new FormControl("",[Validators.required,Validators.maxLength(500)])});
  
get userEmailId(): FormControl{
  return this.feedBackForm.get("userEmailId")as FormControl
}


get message(): FormControl{
  return this.feedBackForm.get("message")as FormControl
}

submitForm(data:any){
  this.url.feedback(data).subscribe(x=>
    {
      console.log("Form Submit");
      this.feedBackForm.reset;
      

    }); 
    this.notification.success({detail:"Your feedback is Valueable for us ....... ",duration:5000});
    ;this.ngOnInit();
    this.router.navigateByUrl("/Home")

}

}
