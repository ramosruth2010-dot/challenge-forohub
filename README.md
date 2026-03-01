<h1 align="center"> 🚧 Proyecto en construcción: Challenge Foro Hub 🚧 </h1>

-> Descripción del proyecto

Foro Hub es una API REST desarrollada en Java con Spring Boot que permite gestionar tópicos de un foro.

El sistema implementa autenticación mediante JWT, persistencia en base de datos MySQL y manejo global de errores, asegurando un acceso seguro y estructurado a los recursos.

-> Tecnologías utilizadas

Java 17+
Spring Boot
Spring Data JPA
Hibernate
Spring Security
JWT (Json Web Token)
MySQL
Flyway
Maven

-> Funcionalidades implementadas

Autenticación

- Generación de token JWT mediante endpoint /login
- Protección de endpoints
- Sesiones stateless

Gestión de tópicos (CRUD)

- Creación de nuevos tópicos
- Listado paginado de tópicos
- Consulta por ID
- Actualización de información
- Eliminación de registros

Validaciones y seguridad

- Validación de datos con @Valid
- Prevención de tópicos duplicados
- Manejo global de excepciones
- Respuestas HTTP adecuadas (400, 401, 404, 409)

-> Atentamente: :shipit:
