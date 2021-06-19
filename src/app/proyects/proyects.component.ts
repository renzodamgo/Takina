import { Component, OnInit } from '@angular/core';
import { DashboardComponent } from '../dashboard/dashboard.component';
import { Proyecto } from '../models/projecto';
import { ProyectoService } from '../proyecto.service';
import { DataService } from '../services/data.service';
import { ActivatedRoute, Router } from '@angular/router';
import { switchMap } from 'rxjs/operators';

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
              private router: Router,) { }

  ngOnInit(): void {
    this.getProyectos();
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
      this.router.navigate(['/proyectos']);
    });
  }

}
