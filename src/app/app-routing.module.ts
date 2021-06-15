import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProyectsComponent } from './proyects/proyects.component';
import { MatSliderModule } from '@angular/material/slider';
import { BusquedaComponent } from './busqueda/busqueda.component';
import { EditProyectComponent } from './edit-proyect/edit-proyect.component';

const routes: Routes = [
  {path: "proyecto",component: ProyectsComponent},
  {path: "",component: DashboardComponent},
  {path: "busqueda",component: BusquedaComponent},
  {path: "proyecto/:id",component: EditProyectComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes),
    MatSliderModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }