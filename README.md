
<h1>Challenge 2 Conversor de literatura📚</h1>
<h2>🔨Desarrollado por</h2>

  -  Alonso Higa Kohatsu

<h2>Descripcion del proyecto📄</h2>
Este es un proyecto desarrollado con Spring Boot utilizando el generador de proyectos Spring Initializr. La aplicación permite realizar la búsqueda de libros dando un request a la API pública de Gutendex y 
esto lo almacena en una base de datos PostreSQL, que más adelante sirve para poder realizar búsquedas por idioma, año o autores, de lo que se haya buscado anteriormente. 

<h2>💥Requisitos previos💥</h2>
>[!WARNING]
> Debes tener PostgreSQL instalado
> Configura tus credenciales de PostgreSQL en el archivo 'application.properties'

<h2>Tecnologías Utilizadas💻</h2>

  - **API de busuqeda de libros**: Gutendex API
  
  - **Base de datos**: PostgreSQL
    
  - **Spring Data JPA**: Acceso a datos relacional

  - **JSON + GSON / Jackson**: Se realizó una conversión de datos desde la API


<h2>Funcionalidades⚙️</h2>

  📚 Se puede realizar una búsqueda de libros por el título a través de la API de Gutendex

  📖 Listado de libros buscados anteriormente
  
  ✅ Verifica automáticamente si el libro ya esta registrado

  👨‍💼 Listado de autores registrados en los libros guardados, incluyendo los títulos asociados a cada uno

  📆 Filtra autores por año: muestra autores que estaban vivos durante un año ingresado por el usuario (según su año de nacimiento o fallecimiento).

  🌍 Búsqueda de libros por idioma
