import { Component, OnInit } from '@angular/core';
import { ProyectoService } from 'src/app/core/services/proyecto.service';
import { Canciones, Proyecto } from 'src/app/models/projecto';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-song',
  templateUrl: './add-song.component.html',
  styleUrls: ['./add-song.component.css']
})
export class AddSongComponent implements OnInit {

  displayedColumns: string[] = ['track', 'nombre', 'duracion'];
  
  project!:Proyecto;
  canciones:Canciones[] = [];
  nombre:string = "";
  id!:number
  constructor(
    private proyectoService:ProyectoService,
    private route:ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.getProject()
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
}
