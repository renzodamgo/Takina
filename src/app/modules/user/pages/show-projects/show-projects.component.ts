import { Component, Input, OnChanges, OnInit } from '@angular/core';
import { ArtistaService } from 'src/app/core/services/artista.service';
import { DataService } from 'src/app/core/services/data.service';
import { ProyectoService } from 'src/app/core/services/proyecto.service';
import { Proyecto } from 'src/app/models/projecto';

@Component({
  selector: 'app-show-projects',
  templateUrl: './show-projects.component.html',
  styleUrls: ['./show-projects.component.css']
})
export class ShowProjectsComponent implements OnChanges {
	@Input() categoria: string = "todos";
	@Input() busqueda: string = "";

	public proyectos:Proyecto[] = [];
	artistaInfo: { [artistaId: string]: string[] } = {};

	constructor(
		public dataService:DataService,
		public artistaService:ArtistaService,
		public proyectoService:ProyectoService
	) { }
	
	ngOnInit(): void {
		this.getTodosProyectos();
	}

	ngOnChanges() {

		if (this.categoria == "ultimos"){
			this.getUltimosProyectos();	
		}

		if (this.categoria == "proyectos" && this.busqueda != ""){
			this.getProyectosPorNombre();
		}
		if (this.categoria == "proyectos" && this.busqueda == ""){
			this.getTodosProyectos();
		}
		if (this.categoria == "genero" && this.busqueda != ""){
			this.getProyectosPorGenero();
		}
	  }

	getProyectosPorGenero(){
		let proyectoGenero: string = this.busqueda;
		this.proyectoService.getProyectosByGenero(proyectoGenero)
			.subscribe(result => {
				this.proyectos = result;

				this.proyectos.forEach((proyecto) => {
					let idChar: string = proyecto.artistaId.toString();
					if (!(idChar in this.artistaInfo)){
						this.artistaService.getArtistaById(proyecto.artistaId)
							.subscribe(artista =>{
								this.artistaInfo[idChar] = [artista.nombre,artista.fotoPerfil];
						});
					}
				});
			},(errorServicio)=>{
				console.log(errorServicio.error.error);
				this.proyectos = [];
			}
		)
	}

	getProyectosPorNombre(){
		let proyectoNombre: string = this.busqueda;
		this.proyectoService.getProyectosByNombre(proyectoNombre)
			.subscribe(result => {
				this.proyectos = result;

				this.proyectos.forEach((proyecto) => {
					let idChar: string = proyecto.artistaId.toString();
					if (!(idChar in this.artistaInfo)){
						this.artistaService.getArtistaById(proyecto.artistaId)
							.subscribe(artista =>{
								this.artistaInfo[idChar] = [artista.nombre,artista.fotoPerfil];
						});
					}
				});
			},(errorServicio)=>{
				console.log(errorServicio.error.error);
				this.proyectos = [];
			}
		)
	}


	getTodosProyectos(){
		this.proyectoService.getAllProyectos()
			.subscribe(result => {
				this.proyectos = result;

				this.proyectos.forEach((proyecto) => {
					let idChar: string = proyecto.artistaId.toString();
					if (!(idChar in this.artistaInfo)){
						this.artistaService.getArtistaById(proyecto.artistaId)
							.subscribe(artista =>{
								this.artistaInfo[idChar] = [artista.nombre,artista.fotoPerfil];
						});
					}
				});
			},(errorServicio)=>{
				console.log(errorServicio.error.error);
				this.proyectos = [];
			}
		)
	}

	getUltimosProyectos(){
		this.proyectoService.getUltimosProyectos()
			.subscribe(result => {
				this.proyectos = result;

				this.proyectos.forEach((proyecto) => {
					let idChar: string = proyecto.artistaId.toString();
					if (!(idChar in this.artistaInfo)){
						this.artistaService.getArtistaById(proyecto.artistaId)
							.subscribe(artista =>{
								this.artistaInfo[idChar] = [artista.nombre,artista.fotoPerfil];
						});
					}
				});
			},(errorServicio)=>{
				console.log(errorServicio.error.error);
				this.proyectos = [];
			}
		)
	}

	infoEmpty(obj:any) {
		return Object.keys(obj).length === 0;
	}

	getArtistaName(artistaId: number){
		let str: string = artistaId.toString();
		return this.artistaInfo[str][0] || "";
	}

	getArtistaAvatar(artistaId: number){
		let str: string = artistaId.toString();
		return this.artistaInfo[str][1] || this.dataService.defaultFotoPerfil;
	}

}
