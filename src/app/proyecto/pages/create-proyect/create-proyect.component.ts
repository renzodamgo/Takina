import { Component, OnInit, HostListener } from '@angular/core';
import { ProyectoService } from '../../../proyecto.service';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Canciones, Proyecto } from '../../../models/projecto';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-create-proyect',
  templateUrl: './create-proyect.component.html',
  styleUrls: ['./create-proyect.component.css']
})
export class CreateProyectComponent implements OnInit {

  displayedColumns: string[] = ['track', 'nombre', 'duracion'];
  
  project!:Proyecto;
  canciones:Canciones[] = [];
  nombre:string = "";
  id!:number

  constructor(
    private location: Location,
    private proyectoService:ProyectoService,
    private route:ActivatedRoute,
    
  ) { }

  ngOnInit(): void {
  }

  createProyecto(){
    // falta crear esta funcion para poder crear un proyecto dentro de la pagina
    console.log(this.project.nombre);
    this.proyectoService.updateProjectoById(this.project)
      .subscribe(data => {
        console.log(data);
        //this.getProject();
      });
    
  }

}
