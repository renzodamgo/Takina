import { Artista } from "./artista";



export interface ArtistaResponse {
    code:    string;
    data:    Artista;
    message: string;
    status:  string;
}

export interface ReqArtista {
    
    biografia:           string;
    departamento:        string;
    fotoPerfil:          string;
    fotoPortada:         string;
    genero:              string;
    id:                  number;
    nombre:              string;
    
    totalReproducciones: number;
    totalSeguidores:     number;
}

interface Administrador {
    fechaRegistro: string;
    nivel:         string;
    usuarioNombre: string;
}

interface Proyecto {
    fecha:       string;
    id:          number;
    lanzamiento: string;
    nombre:      string;
}

interface Seguidor{
    fecha:        string;
    usuarioApodo: string;
}
