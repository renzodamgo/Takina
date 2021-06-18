import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProyectsComponent } from './proyects/proyects.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SearchComponent } from './search/search.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSliderModule } from '@angular/material/slider';
import { MatTabsModule } from '@angular/material/tabs';
import { MatSidenavModule } from '@angular/material/sidenav';
import {MatIconModule} from '@angular/material/icon'; 
import {MatCardModule} from '@angular/material/card'; 
import {MatBottomSheetModule} from '@angular/material/bottom-sheet'; 
import {MatButtonModule} from '@angular/material/button';
import { BusquedaComponent } from './busqueda/busqueda.component'; 
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { EditProyectComponent } from './edit-proyect/edit-proyect.component';
import {MatChipsModule} from '@angular/material/chips';
import {MatTableModule} from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule }   from '@angular/forms';

import { GaugeModule } from 'angular-gauge';


@NgModule({
  declarations: [
    AppComponent,
    ProyectsComponent,
    DashboardComponent,
    SearchComponent,
    BusquedaComponent,
    EditProyectComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSliderModule,
    MatTabsModule,
    MatSidenavModule,
    MatIconModule,
    MatCardModule,
    MatBottomSheetModule,
    MatButtonModule,
    HttpClientModule,
    MatChipsModule,
    MatTableModule,
    MatSelectModule,
    MatFormFieldModule,
    GaugeModule,
    FormsModule
   
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }