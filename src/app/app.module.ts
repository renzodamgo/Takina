import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSliderModule } from '@angular/material/slider';
import { MatTabsModule } from '@angular/material/tabs';
import { MatSidenavModule } from '@angular/material/sidenav';
import {MatIconModule} from '@angular/material/icon'; 
import {MatCardModule} from '@angular/material/card'; 
import {MatBottomSheetModule} from '@angular/material/bottom-sheet'; 
import {MatButtonModule} from '@angular/material/button';


import { BusquedaComponent } from './modules/artista/components/busqueda/busqueda.component'; 
import {  HttpClientModule } from '@angular/common/http';

import {MatChipsModule} from '@angular/material/chips';
import {MatTableModule} from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule }   from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';

import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';

import { GaugeModule } from 'angular-gauge';
import { HeaderComponent } from './core/header/header.component';
import { ArtistaModule } from './modules/artista/artista.module';





@NgModule({
  declarations: [
    AppComponent,
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
    FormsModule,
    MatProgressSpinnerModule,
    MatFormFieldModule,
    MatInputModule,
    MatPaginatorModule,
    MatSortModule,
    ArtistaModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
