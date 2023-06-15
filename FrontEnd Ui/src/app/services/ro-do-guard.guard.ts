import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UrlsService } from './urls.service';

@Injectable({
  providedIn: 'root'
})
export class RoDoGuardGuard implements CanActivate {

constructor(private url:UrlsService,private router:Router){}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      return this.checkLogin(state.url);     
  }

  checkLogin(url:String){
    if(this.url.loggedIn)
{
  this.url.redirectUrl=url; 
   return this.url.loggedIn;
  
}return this.router.navigateByUrl('/Login')
}
  
}
