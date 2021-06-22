import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ProyectsComponent } from './pages/proyects/proyects.component';
import { EditProyectComponent } from './pages/edit-proyect/edit-proyect.component';
import { CreateProyectComponent } from './pages/create-proyect/create-proyect.component';

const routes: Routes = [
  {
    path: '',
    children:[
      {path: '', component: ProyectsComponent},
      {path: 'edit/:id', component: EditProyectComponent},
      {path: 'create', component: CreateProyectComponent},
      
      
    ]
  }
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule,
    CommonModule,
    RouterModule.forChild(routes)
  ]
})
export class ProyectoRoutingModule { }
