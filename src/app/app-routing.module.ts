import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [

	{path: 'usuario',
	loadChildren:()=> import('./modules/user/user.module').then(m=> m.UserModule)},
 
	{path: 'artista',
	loadChildren:()=> import('./modules/artista/artista.module').then(m => m.ArtistaModule)},

	{path: 'auth',
	loadChildren:()=> import('./modules/auth/auth.module').then(m=> m.AuthModule)},

	//{path: '',},
	{path: '**', redirectTo: 'auth', pathMatch: 'full'}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{ useHash: true }),
    ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
