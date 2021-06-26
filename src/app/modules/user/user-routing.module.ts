import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProjectDetailsComponent } from './pages/project-details/project-details.component';
import { ShowProjectsComponent } from './pages/show-projects/show-projects.component';
import { UserDashboardComponent } from './pages/user-dashboard/user-dashboard.component';
import { SearchBarComponent } from './pages/search-bar/search-bar.component';
import { UserComponent } from './user.component';
import { ShowArtistasComponent } from './pages/show-artistas/show-artistas.component';
import { EditUsuarioComponent } from './pages/edit-usuario/edit-usuario.component';
import { UserLibraryComponent } from './pages/user-library/user-library.component';
import { ShowPlaylistComponent } from './pages/show-playlist/show-playlist.component';

const routes: Routes = [
	{path:'', component: UserComponent,
	children:[
		//{path:'proyectos', component:ShowProjectsComponent},
		{path:'proyectos/details/:id', component: ProjectDetailsComponent},
		{path:'administracion', component: ShowArtistasComponent},
		{path:'dashboard',component:UserDashboardComponent},
		{path:'busqueda',component:SearchBarComponent},
		{path:'editar',component:EditUsuarioComponent},
		{path:'biblioteca',component:UserLibraryComponent},
		{path:'biblioteca/details/:id',component:ShowPlaylistComponent},
		{path:'**', redirectTo:'dashboard'}
	]
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
