// Generated by https://quicktype.io

export interface CancionResponse {
    code:    string;
    data:    Cancion[];
    message: string;
    status:  string;
}

interface Cancion {
    audio:       string;
    creditos:    Credito[];
    duracion:    number;
    fotoPortada: string;
    genero:      string;
    id:          number;
    lanzamiento: string;
    nombre:      string;
    proyectoId:  number;
}

interface Credito {
    artistaNombre: string;
    descripcion:   string;
}
