import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ArtistaComponent } from './artista.component';
import { BusquedaComponent } from './components/busqueda/busqueda.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

const routes: Routes = [
  {
    path: '', component:ArtistaComponent,
    children:[
      {path: "proyectos",
        loadChildren:()=> import('./proyecto/proyecto.module').then(m => m.ProyectoModule)},
      {path: "dashboard",component: DashboardComponent},
      {path: "busqueda",component: BusquedaComponent},
      {path: "**", redirectTo: "dashboard"}
    ]
  }
  
  
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ArtistaRoutingModule { }
