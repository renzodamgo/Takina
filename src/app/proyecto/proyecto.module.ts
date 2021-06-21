import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProyectsComponent } from './pages/proyects/proyects.component';
import { EditProyectComponent } from './pages/edit-proyect/edit-proyect.component';
import { CreateProyectComponent } from './pages/create-proyect/create-proyect.component';
import { MatSliderModule } from '@angular/material/slider';
import { MatTabsModule } from '@angular/material/tabs';
import { MatSidenavModule } from '@angular/material/sidenav';
import {MatIconModule} from '@angular/material/icon'; 
import {MatCardModule} from '@angular/material/card'; 
import {MatBottomSheetModule} from '@angular/material/bottom-sheet'; 
import {MatButtonModule} from '@angular/material/button';
import {MatChipsModule} from '@angular/material/chips';
import {MatTableModule} from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule }   from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';

import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from '../app-routing.module';
import { ProyectoRoutingModule } from './proyecto-routing.module';




@NgModule({
  declarations: [
    ProyectsComponent,
    EditProyectComponent,
    CreateProyectComponent,
      
  ],
  imports: [
    CommonModule,
    MatSliderModule,
    MatTabsModule,
    MatSidenavModule,
    MatIconModule,
    MatCardModule,
    MatBottomSheetModule,
    MatButtonModule,
    MatChipsModule,
    MatTableModule,
    MatSelectModule,
    MatFormFieldModule,
    FormsModule,
    MatProgressSpinnerModule,
    MatFormFieldModule,
    MatInputModule,
    MatPaginatorModule,
    MatSortModule,
    RouterModule,
    ProyectoRoutingModule    
    
    
    
  ]
})
export class ProyectoModule { }
