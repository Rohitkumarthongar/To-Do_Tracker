import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutAppComponent } from './components/about-app/about-app.component';
import { AddTaskComponent } from './components/add-task/add-task.component';
import { ArchieveListComponent } from './components/archieve-list/archieve-list.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { DashBoardComponent } from './components/dash-board/dash-board.component';
import { FeaturesComponent } from './components/features/features.component';
import { HighPriorityComponent } from './components/high-priority/high-priority.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { LoginComponent } from './components/login/login.component';
import { LowPriorityComponent } from './components/low-priority/low-priority.component';
import { NotificationComponent } from './components/notification/notification.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { RecycleBinComponent } from './components/recycle-bin/recycle-bin.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { TaskListComponent } from './components/task-list/task-list.component';
import { UpdateProfileComponent } from './components/update-profile/update-profile.component';
import { UpdateTaskComponent } from './components/update-task/update-task.component';
import { UserDataComponent } from './components/user-data/user-data.component';
import { FeedBackComponent } from './feed-back/feed-back.component';
import { RoDoGuardGuard } from './services/ro-do-guard.guard';

const routes: Routes = [
  {
  path: '',
  component: DashBoardComponent, children: [
    {
      path: '',
      component: LoginComponent
    },
   
    {
      path: 'signUp',
      component: SignUpComponent
    }]
},
{path:"Home",component:HomePageComponent,canActivate:[RoDoGuardGuard]},
{path:"Login",component:LoginComponent},
{path:"updateUser",component:UpdateProfileComponent,canActivate:[RoDoGuardGuard]},
{ path:"SignUp",component:SignUpComponent},
{  path:'Dasboard', component:DashBoardComponent,},
{ path:'aboutUs', component:AboutAppComponent,},
{ path:"features" ,component:FeaturesComponent},
{ path:"AddTask" ,component:AddTaskComponent,canActivate:[RoDoGuardGuard]},
{ path:"List" ,component:TaskListComponent,canActivate:[RoDoGuardGuard]},
{path:"notification", component:NotificationComponent,canActivate:[RoDoGuardGuard]},
{path:"updateTask",component:UpdateTaskComponent},
{path:"ArchieveList",component:ArchieveListComponent,canActivate:[RoDoGuardGuard]},
{path:"recycleBin",component:RecycleBinComponent,canActivate:[RoDoGuardGuard]},
{  path: 'forgotpassword',component: ChangePasswordComponent},
{ path: 'feedBack',component: FeedBackComponent,canActivate:[RoDoGuardGuard]},
{path:"userProfile",component:UserDataComponent,canActivate:[RoDoGuardGuard]},
{path:"low",component:HighPriorityComponent,canActivate:[RoDoGuardGuard]},
{path:"high",component:LowPriorityComponent,canActivate:[RoDoGuardGuard]},
{path:"**",component:PageNotFoundComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
