import { Component, OnInit } from '@angular/core';
import { ProyectoService } from '../proyecto.service';

@Component({
  selector: 'app-proyects',
  templateUrl: './proyects.component.html',
  styleUrls: ['./proyects.component.css'],
 
})
export class ProyectsComponent implements OnInit {

  Proyecto!:any;

  constructor(private proyectoService: ProyectoService) { }

  ngOnInit(): void {
    this.getProyectos()
  }

  getProyectos(){
    this.proyectoService.getProyectos(1)
      .subscribe(result=>{
        if (result){ 
          this.Proyecto = (result.data as any);
          console.log(result.data);
          
        }
        
      }, err => {
        console.log(err);
        
      })
  }

}
