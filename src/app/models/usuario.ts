
export interface UsuarioResponse {
    code:    string;
    data:    Usuario;
    message: string;
    status:  string;
}

export interface UsuarioJson {
    apodo: string;
    correo: string;
    fotoPerfil: string;
    id: number;
    nombre: string;
    password: string;
    premium: boolean;
    ultimoIngreso: string;
}

export class Usuario {
    static usuarioFromJson(obj: UsuarioJson){
        return new Usuario(
            obj['apodo'],
    		obj['correo'],
    		obj['fotoPerfil'],
    		obj['id'],
    		obj['nombre'],
    		obj['password'],
    		obj['premium'],
    		obj['ultimoIngreso']
        );
    }

    constructor(
        public apodo: string,
    	public correo: string,
    	public fotoPerfil: string,
    	public id: number,
    	public nombre: string,
    	public password: string,
    	public premium: boolean,
    	public ultimoIngreso: string,
    ){}
}

export class CreateUsuario {

	constructor(
		public apodo: string,
		public correo: string,
		public fotoPerfil: string,
		public nombre: string,
		public password: string,
	){}
}

export class EditUsuario {

	static editUsuarioFromUsuario(obj: Usuario){
		return new EditUsuario(
			obj.correo,
			obj.fotoPerfil,
			obj.id,
			obj.nombre,
			obj.password,
		);
	}

	constructor(
		public correo: string,
		public fotoPerfil: string,
		public id: number,
		public nombre: string,
		public password: string
	){}
}