import { Component, OnInit, HostListener } from '@angular/core';
import { ProyectoService } from '../../../proyecto.service';
import { Canciones, Proyecto } from '../../../models/projecto';
import { ActivatedRoute } from '@angular/router';
import { DataService } from 'src/app/services/data.service';


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
    private dataService:DataService
    
  ) { }

  ngOnInit(): void {
    console.log('hi')
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

  createProyecto(){
    this.project.artistaId = this.dataService.artistaId ;
    if (this.project.id) {
      // Actualizamos en vez de crear
      this.proyectoService.updateProjectoById(this.project)
      .subscribe(data => {
        console.log(data);
        this.getProject();
      }
      )
    } else {
      // crear
      this.proyectoService.addProjecto(this.project)
      .subscribe( proyecto => {
        console.log(proyecto);
        this.getProject();
      })
    }
  }

}
