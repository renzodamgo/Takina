import { Component, OnInit } from '@angular/core';
import { ProyectoService } from '../proyecto.service';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}
const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},
  {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He'},
  {position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li'},
  {position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be'},
  {position: 5, name: 'Boron', weight: 10.811, symbol: 'B'},
  {position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C'},
  {position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N'},
  {position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O'},
  {position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F'},
  {position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne'},
];

@Component({
  selector: 'app-edit-proyect',
  templateUrl: './edit-proyect.component.html',
  styleUrls: ['./edit-proyect.component.css']
})




export class EditProyectComponent implements OnInit {

  displayedColumns: string[] = ['position', 'name', 'weight', 'symbol'];
  dataSource = ELEMENT_DATA;
  project!:any;
  name:string = "Bue"
  id!:number

  constructor(
    private location: Location,
    private proyectoService:ProyectoService,
    private route:ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.getProject()
  }

  goBack(): void {
    this.location.back();
  }

  getProject(){
    this.id  = +this.route.snapshot.paramMap.get('id')!;
    this.proyectoService.getProyectoById(this.id)
      .subscribe((result)=>{
        this.project = (result.data as any);
          console.log(result.data);
          console.log(this.project.discografica);
      })
  }

}

