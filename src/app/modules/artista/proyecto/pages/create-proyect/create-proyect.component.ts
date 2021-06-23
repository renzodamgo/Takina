import { Component, OnInit, HostListener } from '@angular/core';
import { ProyectoService } from '../../../../../core/services/proyecto.service';
import { Canciones, Proyecto } from '../../../../../models/projecto';
import { ActivatedRoute, Router } from '@angular/router';
import { DataService } from 'src/app/core/services/data.service';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-create-proyect',
  templateUrl: './create-proyect.component.html',
  styleUrls: ['./create-proyect.component.css']
})
export class CreateProyectComponent implements OnInit {

  displayedColumns: string[] = ['track', 'nombre', 'duracion'];
  
  project:Proyecto = new Proyecto(
    0,[],'','',0,'','','',0,'','',0,''
  );
  
  canciones:Canciones[] = [];
  nombre:string = "";
  id!:number

  constructor(
    private proyectoService:ProyectoService,
    private route:ActivatedRoute,
    private dataService:DataService,
    private _snackBar:MatSnackBar,
    private router:Router,
    
  ) { }

  ngOnInit(): void {
    
  }

  createSnackBar() {
    this._snackBar.open("El proyecto ha sido creado", "OK" );
  }



  createProyecto(){
    this.project.artistaId = this.dataService.artistaId ;
    
    
      // crear
    this.proyectoService.addProjecto(this.project)
    .subscribe( proyecto => {
      console.log(proyecto);
      
      this.id = (proyecto.data.id as number);

      console.log(`projectoId: ${this.id}`);

      this.addSong();
      //this.createSnackBar()
    })
    
  }

  addSong(){
    this.router.navigate(['/proyectos/edit', this.id]);
  }

}
