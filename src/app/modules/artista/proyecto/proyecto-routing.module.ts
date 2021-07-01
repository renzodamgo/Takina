import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ProyectsComponent } from './pages/proyects/proyects.component';
import { EditProyectComponent } from './pages/edit-proyect/edit-proyect.component';
import { CreateProyectComponent } from './pages/create-proyect/create-proyect.component';
import { AddSongComponent } from './pages/add-song/add-song.component';

const routes: Routes = [
  {
    path: '',
    children:[
      {path: '', component: ProyectsComponent},
      {path: 'edit/:id', component: EditProyectComponent},
      {path: 'edit/:id/add', component: AddSongComponent},
      {path: 'create', component: CreateProyectComponent},
      // {path: '**',redirectTo: ''}
    ]
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProyectoRoutingModule { }
