import { Component, OnInit } from '@angular/core';
import { ProyectoService } from '../../../../../core/services/proyecto.service';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Canciones, Proyecto } from '../../../../../models/projecto';
import { MatSnackBar } from '@angular/material/snack-bar';



@Component({
  selector: 'app-edit-proyect',
  templateUrl: './edit-proyect.component.html',
  styleUrls: ['./edit-proyect.component.css']
})

export class EditProyectComponent implements OnInit {

  displayedColumns: string[] = ['track', 'nombre', 'duracion'];
  
  project!:Proyecto;
  canciones:Canciones[] = [];
  nombre:string = "";
  id!:number

  constructor(
    private location: Location,
    private proyectoService:ProyectoService,
    private route:ActivatedRoute,
    private _snackBar: MatSnackBar
    
  ) { }

  ngOnInit(): void {
    this.getProject()
  }

  goBack() {
    this.location.back();
  }
  editSnackBar() {
    this._snackBar.open("El proyecto ha sido editado", "OK" );
  }

  getProject(){
    this.id  = +this.route.snapshot.paramMap.get('id')!;
    this.proyectoService.getProyectoById(this.id)
      .subscribe((result)=>{
        this.project = result;
        this.canciones = result.canciones;
        this.nombre = result.nombre;
        console.log(this.project);
        console.log(this.canciones);
      })

    
  }
  editProyecto(){
    
    console.log(this.project.nombre);
    this.proyectoService.updateProjectoById(this.project)
      .subscribe(data => {
        console.log(data);
        this.getProject();
        this.editSnackBar()
      });
    
  }

}

