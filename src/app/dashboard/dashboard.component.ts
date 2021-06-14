import { Component, OnInit } from '@angular/core';
import { ArtistaService } from '../artista.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  public Artista!:any;
 


  constructor(private artistaService:ArtistaService) { }

  ngOnInit(): void {
     this.getArtista()
  }

  getArtista(){
    this.artistaService.getArtista()
      .subscribe(result=>{
        if (result){ 
          this.Artista = (result.data as any);
          console.log(result.data);
          
        }
        
      }, err => {
        console.log(err);
        
      })
  }
  

}
