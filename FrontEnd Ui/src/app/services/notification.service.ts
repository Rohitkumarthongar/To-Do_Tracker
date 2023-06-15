import { Injectable } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

//   constructor(private toastr: ToastrService) { }
//   showSuccess(message: any, title: any) {
//     this.toastr.success(message, title)
//   }
//   showError(message: any, title: any) {
//     this.toastr.error(message, title)
//   }
// showFalure(message:any,title:any){
//   this.toastr.info(message,title)
// }

constructor(  private toast: NgToastService ) { }

ngOnInit(): void {
}

showSuccess() {
  this.toast.success({detail:"SUCCESS",summary:'Your Success Message',duration:5000});
}

showError() {
  this.toast.error({detail:"ERROR",summary:'Your Error Message',sticky:true});
}

showInfo() {
  this.toast.info({detail:"INFO",summary:'Your Info Message',sticky:true});
}

// showWarn() {
//   this.toast.warn({detail:"WARN",summary:'Your Warn Message',duration:'5000'});
// }

}
