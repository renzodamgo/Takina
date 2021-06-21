import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';

import { BusquedaComponent } from './busqueda/busqueda.component';


const routes: Routes = [
 
  {path: "proyectos",
  loadChildren:()=> import('./proyecto/proyecto.module').then(m => m.ProyectoModule)},
  {path: "dashboard",component: DashboardComponent},
  {path: "busqueda",component: BusquedaComponent},
  {path: "**",redirectTo: "dashboard"}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes),
    ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
