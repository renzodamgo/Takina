import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';



const routes: Routes = [
 
  {path: "artista",
  loadChildren:()=> import('./modules/artista/artista.module').then(m => m.ArtistaModule)},

  {path: "auth",
  loadChildren:()=> import('./modules/auth/auth.module').then(m=> m.AuthModule)},
  
  {path: "user",
  loadChildren:()=> import('./modules/user/user.module').then(m=> m.UserModule)},

  // {path: "**",redirectTo: "auth"}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes),
    ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
