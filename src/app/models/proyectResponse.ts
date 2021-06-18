// Generated by https://quicktype.io

import { Proyecto } from "./projecto";


export interface ProjectosResponse {
    code:    string;
    data:    Proyecto[];
    message: string;
    status:  string;
}

export interface ProjectoResponse {
    code:    string;
    data:    Proyecto;
    message: string;
    status:  string;
}

export interface Projecto {
    artistaId:    number;
    canciones:    Canciones[];
    descripcion:  string;
    discografica: string;
    duracion:     number;
    fecha:        string;
    fotoPortada:  string;
    genero:       string;
    id:           number;
    lanzamiento:  string;
    nombre:       string;
    numCanciones: number;
    tipo:         string;
}

export interface Canciones {
    duracion: number;
    nombre:   string;
    track:    number;
}
