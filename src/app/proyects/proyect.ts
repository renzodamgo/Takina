export interface Data{
    data:Proyect[],
}

export interface Proyect {
    id: 1,
    nombre: string;
    tipo: string;
    duracion: number;
    descripcion: string,
    numCanciones: number,
    lanzamiento: Date,
    fecha: Date,
    discografica: string,
    fotoPortada:string,
    genero: string,
    artistaId: number,
    canciones: []
    
}

