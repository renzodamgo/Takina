# Takina RESTAPI
Backend de aplicación web Takina, !el nuevo reproductor musical para la industria peruana!

Landing Page: https://frany-oss.github.io/Takina/
Swagger: https://takina.herokuapp.com/swagger-ui.html


## To Do:
- [?] como implementamos un album de fotos para cada artista - US019
- [?] descarga de contenido - US012

## To Fix:
- [X] (usuario) eliminar usuario - US037 : necesita fix. si un artista no tiene otro administrador mas que el usuario siendo eliminado, se elimina al artista, al igual que todas sus mercancias, proyectos musicales, canciones, reproducciones y administradores.

## Acceptance criteria:
- [X] (proyecto) validacion de creacion artista no puede tener 2 proyectos del mismo nombre
- [X] (cancion) validacion de creacion proyectos no puede tener 2 canciones del mismo nombre
- [X] (cancion) cancion tiene un track number en un proyecto musical
- [ ] (cancion) validacion que los numeros de track de las canciones eliminadas se guarden en un arreglo para que sean asignadas automaticamente a la siguiente cancion añadida al proyecto musical

## Rutina de postman:
- Borrar y crear base de datos Takina
- Correr todos los Create (RUN)
- Verificar los GET
- Verificar los PUT
- Verificar los DELETE