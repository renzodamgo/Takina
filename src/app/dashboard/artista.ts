export interface Artista{
    data:Data[],
}

interface Data {
    table: string;
    name: string;
    datatype: string;
    id: 1,
    nombre: string;
    fotoPerfil: string;
    fotoPortada: string;
    biografia: string;
    totalSeguidores: number,
    totalReproducciones: number,
    departamento: string;
    genero: string;
    
}