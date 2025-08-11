<h1>📚 Challenge API - Sistema de Gestión de Tópicos</h1>

<h2>🔨 Desarrollado por</h2>

- [Alonso Higa]

<h2>📄 Descripción del Proyecto</h2>

API REST construida con <strong>Spring Boot</strong> para la gestión de usuarios y tópicos de discusión.  
Incluye autenticación <strong>JWT</strong>, validación de datos y manejo global de errores.

<h2> Requisitos Previos</h2>

>[!WARNING]  
> 💾 Debes tener <strong>MySQL</strong> instalado y ejecutándose

>[!WARNING]  
> ⚙️ Configura tus credenciales en el archivo <code>application.properties</code>

<h2>💻 Tecnologías Utilizadas</h2>

- <strong>Lenguaje:</strong> Java 17  
- <strong>Framework:</strong> Spring Boot 3.5.x  
- Spring Security  
- Spring Data JPA  
- MySQL  
- Flyway (migraciones)  
- JWT (Auth0)  
- Lombok

<h2>⚙️ Configuración del Proyecto</h2>

<h3>1. Base de Datos (MySQL)</h3>

Configura tu archivo <code>application.properties</code>:

<pre><code>
spring.datasource.url=jdbc:mysql://localhost:3306/challenge
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true

spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
</code></pre>

<h3>2. Ejecución del Proyecto</h3>

Usa el siguiente comando para iniciar la aplicación:

<pre><code>./mvnw spring-boot:run</code></pre>

<h2>🔐 Autenticación</h2>

<h3>Endpoint de Login</h3>

<code>POST /login</code>  
Requiere credenciales válidas de usuario y devuelve un token JWT.

<h4>📥 Ejemplo de Request:</h4>

<pre><code>{
  "login": "usuario123",
  "contrasenha": "secreta"
}
</code></pre>

> Todos los endpoints protegidos requieren un JWT válido en el header:
> <code>Authorization: Bearer &lt;token&gt;</code>

<h2>📌 Endpoints Principales</h2>

<table>
  <thead>
    <tr>
      <th>Método</th>
      <th>Ruta</th>
      <th>Descripción</th>
    </tr>
  </thead>
  <tbody>
    <tr><td>POST</td><td>/topicos</td><td>Registrar un tópico</td></tr>
    <tr><td>GET</td><td>/topicos</td><td>Listar todos los tópicos</td></tr>
    <tr><td>GET</td><td>/topicos/{id}</td><td>Obtener detalle de un tópico</td></tr>
    <tr><td>PUT</td><td>/topicos</td><td>Actualizar un tópico</td></tr>
    <tr><td>DELETE</td><td>/topicos/{id}</td><td>Desactivar un tópico</td></tr>
  </tbody>
</table>

<h2>❗ Manejo de Errores</h2>

Los errores de validación y recursos no encontrados son manejados por <code>GestorDeErrores.java</code>, devolviendo respuestas claras y estructuradas al cliente.

<h2>📝 Notas Finales</h2>

- Asegúrate de que las migraciones Flyway se ejecuten correctamente al iniciar la aplicación.
- Todos los endpoints protegidos requieren un JWT válido.
- La lógica de usuarios y tópicos es fácilmente extensible para futuras funcionalidades.
