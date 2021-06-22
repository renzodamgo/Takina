import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProjectDetailsComponent } from './pages/project-details/project-details.component';
import { ShowProjectsComponent } from './pages/show-projects/show-projects.component';
import { UserDashboardComponent } from './pages/user-dashboard/user-dashboard.component';
import { UserComponent } from './user.component';

const routes: Routes = [
  {path:"", component: UserComponent,
  children:[
    {path:"proyectos", component:ShowProjectsComponent},
    {path:"proyectos/details/:id", component: ProjectDetailsComponent},
    {path:"dashboard",component:UserDashboardComponent},
    {path:"", redirectTo:"dashboard"}
  ]
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
