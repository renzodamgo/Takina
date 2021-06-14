import { Component, OnInit } from '@angular/core';
import { DashboardComponent } from '../dashboard/dashboard.component';
import { ProyectoService } from '../proyecto.service';

@Component({
  selector: 'app-proyects',
  templateUrl: './proyects.component.html',
  styleUrls: ['./proyects.component.css'],
  
 
})
export class ProyectsComponent implements OnInit {

  proyecto!:any;
  userUrl:string="https://upload.wikimedia.org/wikipedia/commons/1/1f/Woman_1.jpg";

  constructor(private proyectoService: ProyectoService) { }

  ngOnInit(): void {
    this.getProyectos()
  }

  getProyectos(){
    this.proyectoService.getProyectos(1)
      .subscribe(result=>{
        if (result){ 
          this.proyecto = (result.data as any);
          console.log(result.data);
          
        }
        
      }, err => {
        console.log(err);
        
      })
    
  }

}
