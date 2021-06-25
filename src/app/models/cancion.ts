
export interface CancionResponse {
	code: string;
	data: Cancion;
	message: string;
	status: string;
}

export interface CancionesResponse {
	code: string;
	data: Cancion[];
	message: string;
	status: string;
}


export interface CancionJson {
	audio: string;
	duracion: number;
	fotoPortada: string;
	genero: string;
	id: number;
	lanzamiento: string;
	nombre: string;
	proyectoId: number;
	track: number;
}

export class Cancion {
    static cancionFromJson(obj: CancionJson){
        return new Cancion(
            obj['audio'],
			obj['duracion'],
			obj['fotoPortada'],
			obj['genero'],
			obj['id'],
			obj['lanzamiento'],
			obj['nombre'],
			obj['proyectoId'],
			obj['track'],
        );
    }

	static durationFromFloat(value: number): string {
		let min: number = value | 0;
		let seg: number = ((value-min)*60) | 0;
		if(seg == 0 && min == 0) return `0:00`;
		if(seg == 0) return `${min}:00`;
		if(seg < 10) return `${min}:0${seg}`;
		if(min == 0) return `0:${seg}`;
		return `${min}:${seg}`;
	}

    constructor(
        public audio: string,
		public duracion: number,
		public fotoPortada: string,
		public genero: string,
		public id: number,
		public lanzamiento: string,
		public nombre: string,
		public proyectoId: number,
		public track: number,
    ){}
}

export class CreateCancion {
	constructor(
		public audio: string,
		public duracion: number,
		public nombre: string,
		public proyectoId: number,
	){}
}