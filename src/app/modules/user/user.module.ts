import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatChipsModule } from '@angular/material/chips';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSelectModule } from '@angular/material/select';
import { MatTabsModule } from '@angular/material/tabs';
import { ProjectDetailsComponent } from './pages/project-details/project-details.component';
import { SearchBarComponent } from './pages/search-bar/search-bar.component';
import { ShowArtistasComponent } from './pages/show-artistas/show-artistas.component';
import { ShowProjectsComponent } from './pages/show-projects/show-projects.component';
import { UserDashboardComponent } from './pages/user-dashboard/user-dashboard.component';
import { UserRoutingModule } from './user-routing.module';
import { MatButtonModule } from '@angular/material/button';
import { EditUsuarioComponent } from './pages/edit-usuario/edit-usuario.component';
import { UserComponent } from './user.component';
import { MatCardModule } from '@angular/material/card'; 
import { MatBottomSheetModule } from '@angular/material/bottom-sheet'; 
import { MatSliderModule } from '@angular/material/slider';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { RouterModule } from '@angular/router';
import { MatMenuModule } from '@angular/material/menu';
import { UserLibraryComponent } from './pages/user-library/user-library.component';
import { ShowPlaylistComponent } from './pages/show-playlist/show-playlist.component';
import { MatDialogModule } from '@angular/material/dialog';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { PopUpDialog } from './pages/user-library/user-library.component';
import { CreateArtistaComponent } from './pages/create-artista/create-artista.component';
import { EditArtistaComponent } from './pages/edit-artista/edit-artista.component';

@NgModule({
	declarations: [
		ShowProjectsComponent,
		ProjectDetailsComponent,
		UserComponent,
		UserDashboardComponent,
		SearchBarComponent,
		ShowArtistasComponent,
		EditUsuarioComponent,
  		UserLibraryComponent,
		ShowPlaylistComponent,
		PopUpDialog,
  		CreateArtistaComponent,
    EditArtistaComponent
	],
	imports: [
		CommonModule,
		UserRoutingModule,
		MatIconModule,
		MatChipsModule,
		MatProgressSpinnerModule,
		MatSelectModule,
		FormsModule,
		MatTabsModule,
		MatCardModule,
		MatButtonModule,
		MatSnackBarModule,
		MatFormFieldModule,
		MatInputModule,
		MatTableModule,
		MatMenuModule,
		MatDialogModule,
	],
	schemas: [
		CUSTOM_ELEMENTS_SCHEMA
	],
})
export class UserModule { }
