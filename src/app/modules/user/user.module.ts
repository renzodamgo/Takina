import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { ShowProjectsComponent } from './pages/show-projects/show-projects.component';
import { ProjectDetailsComponent } from './pages/project-details/project-details.component';
import { UserComponent } from './user.component';
import { UserDashboardComponent } from './pages/user-dashboard/user-dashboard.component';

import {MatIconModule} from '@angular/material/icon'; 
import {MatChipsModule} from '@angular/material/chips';
import {MatSelectModule} from '@angular/material/select';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { MatTabsModule } from '@angular/material/tabs';
import { FormsModule }   from '@angular/forms';

@NgModule({
  declarations: [
    ShowProjectsComponent,
    ProjectDetailsComponent,
    UserComponent,
    UserDashboardComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    MatIconModule,
    MatChipsModule,
    MatProgressSpinnerModule,
    MatSelectModule,
    FormsModule,
    MatTabsModule
  ]
})
export class UserModule { }
