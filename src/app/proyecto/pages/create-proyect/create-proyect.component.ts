import { Component, OnInit, HostListener } from '@angular/core';

import { Canciones, Proyecto } from '../../../models/projecto';


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
 
    
  ) { }

  ngOnInit(): void {
    console.log('hila')
  }
  createProyecto(){
    console.log(this.project.nombre)
  }



}
