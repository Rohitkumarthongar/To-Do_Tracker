import { NgModule } from '@angular/core';

import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomePageComponent } from './components/home-page/home-page.component';
import { LoginComponent } from './components/login/login.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { FeaturesComponent } from './components/features/features.component';
import { AboutAppComponent } from './components/about-app/about-app.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { TaskListComponent } from './components/task-list/task-list.component';
import { DashBoardComponent } from './components/dash-board/dash-board.component';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatSliderModule } from '@angular/material/slider';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatDividerModule } from '@angular/material/divider';
import { MatListModule } from '@angular/material/list';
import { MatStepperModule } from '@angular/material/stepper';
import { MatTabsModule } from '@angular/material/tabs';
import { MatTreeModule } from '@angular/material/tree';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatBottomSheetModule } from '@angular/material/bottom-sheet';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatSortModule } from '@angular/material/sort';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatBadgeModule} from '@angular/material/badge';
import {MatButtonModule} from '@angular/material/button';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatCardModule} from '@angular/material/card';
import {MatChipsModule} from '@angular/material/chips';
import {MatDialogModule} from '@angular/material/dialog';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatMenuModule} from '@angular/material/menu';
import {MatNativeDateModule, MatRippleModule} from '@angular/material/core';
import {MatTableModule} from '@angular/material/table';
import {MatToolbarModule} from '@angular/material/toolbar';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HttpClientJsonpModule } from '@angular/common/http';
import { AddTaskComponent } from './components/add-task/add-task.component';
import { NotificationComponent } from './components/notification/notification.component';
import { ValidateEqualModule } from 'ng-validate-equal';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { UpdateProfileComponent } from './components/update-profile/update-profile.component';
import { UpdateTaskComponent } from './components/update-task/update-task.component';
import { FilterPipe } from './components/pipe/filter.pipe';
import { DetailedListComponent } from './components/detailed-list/detailed-list.component';
import { RecycleBinComponent } from './components/recycle-bin/recycle-bin.component';
import { ArchieveListComponent } from './components/archieve-list/archieve-list.component'; 
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { UserDataComponent } from './components/user-data/user-data.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { ToastrModule } from 'ngx-toastr';  
import { NgToastModule } from 'ng-angular-popup';
import { FeedBackComponent } from './feed-back/feed-back.component';
import { HighPriorityComponent } from './components/high-priority/high-priority.component';
import { LowPriorityComponent } from './components/low-priority/low-priority.component'



@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    LoginComponent,
    SignUpComponent,
    FeaturesComponent,
    AboutAppComponent,
    ChangePasswordComponent,
    TaskListComponent,
    DashBoardComponent,
    AddTaskComponent,
    NotificationComponent,
    PageNotFoundComponent,
    UpdateProfileComponent,
    UpdateTaskComponent,
    FilterPipe,
    DetailedListComponent,
    RecycleBinComponent,
    ArchieveListComponent,
    UserDataComponent,
    FeedBackComponent,
    HighPriorityComponent,
    LowPriorityComponent,

    
  ],
  imports: [
    NgToastModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      timeOut: 10000,
      positionClass: 'toast-top-right',
      preventDuplicates: true,
    }),
    NgxPaginationModule,
    MatSnackBarModule,
    ValidateEqualModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSelectModule,
    MatSlideToggleModule,
    MatSidenavModule,
    MatSliderModule,
    ReactiveFormsModule,
    CommonModule,
    MatCheckboxModule,
    MatRadioModule,
    MatDividerModule,
    MatTabsModule,
    MatTreeModule,
    MatStepperModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatBottomSheetModule,
   BrowserModule,
   FormsModule,
   MatSnackBarModule,
   MatToolbarModule,
   MatTooltipModule,
   MatSortModule,MatListModule,
   BrowserAnimationsModule,
   HttpClientModule,
  MatAutocompleteModule,
  MatBadgeModule,
  MatButtonModule,
  MatButtonToggleModule,
  MatCardModule,
  MatChipsModule,
  MatDatepickerModule,
  MatDialogModule,
  MatExpansionModule,
  MatFormFieldModule,
  MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatMenuModule,
  MatPaginatorModule,
  MatRippleModule,
  MatSidenavModule,
  MatTableModule,
  MatToolbarModule,
  AppRoutingModule,
  MatNativeDateModule, 
  HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
