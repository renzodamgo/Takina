import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ArtistaRoutingModule } from './artista-routing.module';
import { ArtistaComponent } from './artista.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { BusquedaComponent } from './components/busqueda/busqueda.component';


import {MatIconModule} from '@angular/material/icon'; 
import {MatChipsModule} from '@angular/material/chips';
import {MatSelectModule} from '@angular/material/select';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { MatTabsModule } from '@angular/material/tabs';
import { FormsModule }   from '@angular/forms';


@NgModule({
  declarations: [
    ArtistaComponent,
    DashboardComponent,
    BusquedaComponent
  
  ],
  imports: [
    CommonModule,
    ArtistaRoutingModule,
    MatIconModule,
    MatChipsModule,
    MatProgressSpinnerModule,
    MatSelectModule,
    FormsModule,
    MatTabsModule

    
  ],

  exports:[
    DashboardComponent,
  ],
  providers: [],
  bootstrap: [ArtistaComponent]
})
export class ArtistaModule { }
