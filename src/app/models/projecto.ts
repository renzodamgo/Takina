export class Proyecto{
    
    static proyectoFromJson( obj: Object){
        return new Proyecto(
            obj['artistaId'],
            obj['canciones'],
            obj['descripcion'],
            obj['discografica'],
            obj['duracion'],
            obj['fecha'],
            obj['fotoPortada'],
            obj['genero'],
            obj['id'],
            obj['lanzamiento'],
            obj['nombre'],
            obj['numCanciones'],
            obj['tipo'],
        );
    }

    constructor(
        public artistaId:    number,
        public canciones:    Canciones[],
        public descripcion:  string,
        public discografica: string,
        public duracion:     number,
        public fecha:        string,
        public fotoPortada:  string,
        public genero:       string,
        public id:           number,
        public lanzamiento:  string,
        public nombre:       string,
        public numCanciones: number,
        public tipo:         string,
    ){}
}


export class Canciones{
    constructor(
        public duracion: number,
        public nombre:   string,
        public track:    number,
    ){

    }
}