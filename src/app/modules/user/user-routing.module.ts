import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProjectDetailsComponent } from './pages/project-details/project-details.component';
import { ShowProjectsComponent } from './pages/show-projects/show-projects.component';
import { UserDashboardComponent } from './pages/user-dashboard/user-dashboard.component';
import { SearchBarComponent } from './pages/search-bar/search-bar.component';
import { UserComponent } from './user.component';
import { ShowArtistasComponent } from './pages/show-artistas/show-artistas.component';

const routes: Routes = [
	{path:'', component: UserComponent,
	children:[
		{path:'proyectos', component:ShowProjectsComponent},
		{path:'proyectos/details/:id', component: ProjectDetailsComponent},
		{path:'administracion', component: ShowArtistasComponent},
		{path:'dashboard',component:UserDashboardComponent},
		{path:'busqueda',component:SearchBarComponent},
		{path:'**', redirectTo:'dashboard'}
	]
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
