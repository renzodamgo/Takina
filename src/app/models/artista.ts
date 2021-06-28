import {  ReqArtista } from "./artistaResponse";

export class Artista{

    static artistaFromJson( obj: ReqArtista){
        return new Artista(
            obj['biografia'],
            obj['departamento'], 
            obj['fotoPerfil'],
            obj['fotoPortada'], 
            obj['genero'],       
            obj['id'],  
            obj['nombre'],   
            obj['totalReproducciones'],
            obj['totalSeguidores'],

        )
    }

    constructor(
        public biografia:           string,
        public departamento:        string,
        public fotoPerfil:          string,
        public fotoPortada:         string,
        public genero:              string,
        public id:                  number,
        public nombre:              string,
        public totalReproducciones: number,
        public totalSeguidores:     number,
    ){}

    
}

export class CreateArtista {
	constructor(
		public biografia: string,
		public departamento: string,
		public fotoPerfil: string,
		public fotoPortada: string,
		public genero: string,
		public nombre: string,
		public usuarioId: number
	) {}
}

export class Administrador{
    constructor(
        public fechaRegistro: string,
        public nivel:         string,
        public usuarioNombre: string,
    ){}
}

export class Proyecto{
    constructor(
        public fecha:       string,
        public id:          number,
        public lanzamiento: string,
        public nombre:      string,
    ){}
}

export class Seguidor{
    constructor(
        public fecha:        string,
        public usuarioApodo: string,
        
    ){}
}