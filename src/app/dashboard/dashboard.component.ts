import { Component, OnInit } from '@angular/core';
import { ArtistaService } from '../artista.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  data!:any[];
  nombre!:string;


  constructor(private artistaService:ArtistaService) { }

  ngOnInit(): void {
     this.getArtista()
  }

  getArtista(){
    this.artistaService.getArtista()
      .subscribe(result=>{
        if (result){ 
          this.data = result.data;
          console.log(result.data);
          console.log((this.data as any).nombre)
        }
        
      }, err => {
        console.log(err);
        
      })
  }

}
