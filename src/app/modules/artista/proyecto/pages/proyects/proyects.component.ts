import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

import { Proyecto } from '../../../../../models/projecto';
import { ProyectoService } from '../../../../../core/services/proyecto.service';
import { DataService } from '../../../../../core/services/data.service';

@Component({
  selector: 'app-proyects',
  templateUrl: './proyects.component.html',
  styleUrls: ['./proyects.component.css'],
  
 
})
export class ProyectsComponent implements OnInit {
  
  proyectos:Proyecto[] = [];
  userUrl:string = 'https://upload.wikimedia.org/wikipedia/commons/1/1f/Woman_1.jpg';

  constructor(private proyectoService: ProyectoService,
              public  dataService:DataService,
              private _snackBar:MatSnackBar
              ) { }

  ngOnInit(): void {
    this.getProyectos();
  }

  deleteSnackBar() {
    this._snackBar.open("El proyecto ha sido eliminado", "OK" );
  }

  getProyectos(){
    this.userUrl = this.dataService.fotoPerfilUrl;
    this.proyectoService.getProyectos(this.dataService.artistaId)
      .subscribe(result=>{
        this.proyectos= result;
        console.log(this.proyectos)
        
        console.log(this.userUrl)
        }
      
      )
    
  }

  deleteProyecto(id: number) {
    this.proyectoService.deleteProyectoById(id)
    .subscribe( result => {
      // No es necesario usar navigate, usa un get para que se actualizen los datos y angular lo renderiza real-time wow
      // Si quisieras usar el navigate sería (['']) porque ya estas dentro de (/proyecto) 
      // this.router.navigate(['/proyectos']); aqui estarías entrando a /proyecto/proyectos
      // y yo puse en el routing que cuando entras a un route q no existe te manda al dashboard de artista con el ['**']
      console.log(result)
      this.getProyectos();
      this.deleteSnackBar()
    });
  }

}
