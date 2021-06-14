import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProyectsComponent } from './proyects/proyects.component';
import { MatSliderModule } from '@angular/material/slider';

const routes: Routes = [
  {path: "",component: ProyectsComponent},
  {path: "home",component: DashboardComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes),
    MatSliderModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
