import { Component, OnInit, HostListener } from '@angular/core';
import { ProyectoService } from '../proyecto.service';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Canciones, Proyecto } from '../models/projecto';
import { FormBuilder } from '@angular/forms';


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
    
  ) { }

  ngOnInit(): void {
    this.getProject()
  }

  @HostListener('click')
  goBack() {
    this.location.back();
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
      });
    
  }

}

