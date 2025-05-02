#🛒 Microservicio de Gestión de Productos

Este microservicio se encarga de la gestión del catálogo de productos, permitiendo crear, editar, consultar y eliminar productos. Forma parte de una arquitectura de microservicios y está desarrollado con Spring Boot.

📦 Tecnologías
Java 17+

Spring Boot 

Spring Data JPA

PostgreSQL (Neon Database)

Maven

Lombok

⚙️ Configuración
Variables de entorno requeridas
Asegúrate de definir las siguientes variables de entorno:

Variable	Descripción	Ejemplo
DB_URL	URL de conexión a la base de datos Neon	[jdbc:postgresql://ep-xyz.us-east-2.aws.neon.tech/productdb](https://console.neon.tech/app/projects/orange-bread-10311465)


🚀 Endpoints principales
Método	Endpoint	Descripción
POST	/product/create	Crea un nuevo producto
PUT	/product/edit/{id}	Edita un producto existente
DELETE	/product/delete/{id}	Elimina un producto por su ID
GET	/product/search/{id}	Busca un producto por su ID
GET	/product/all	Lista todos los productos

Base URL por defecto: http://localhost:8080

🛡️ Seguridad
Nota: Este microservicio puede integrarse con un microservicio de autenticación para proteger sus endpoints.

Los endpoints pueden estar protegidos con JWT (según configuración global).
