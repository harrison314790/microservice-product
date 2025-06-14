# 🛒 Microservicio de Gestión de Productos

Este microservicio se encarga de la **gestión del catálogo de productos**, permitiendo crear, editar, consultar y eliminar productos.  
Forma parte de una arquitectura de microservicios y está desarrollado con **Spring Boot**.

---

## 📦 Tecnologías

- Java 17+  
- Spring Boot  
- Spring Data JPA  
- PostgreSQL (Neon Database)  
- Maven  
- Docker  
- Lombok  

---

## ⚙️ Configuración

### Variables de entorno requeridas

Asegúrate de definir las siguientes variables de entorno:

| Variable   | Descripción                             | Ejemplo                                                                 |
|------------|-----------------------------------------|-------------------------------------------------------------------------|
| DB_URL     | URL de conexión a la base de datos Neon | [jdbc:postgresql://ep-xyz.us-east-2.aws.neon.tech/productdb](https://console.neon.tech/app/projects/orange-bread-10311465) |

---


## 🔐 Credenciales de prueba

| Usuario  | Rol       | Contraseña |
|----------|-----------|------------|
| harrison | ADMIN     | 1234       |
| juan     | CLIENT    | 1234       |
| camilo   | DELIVERER | 1234       |

---

## 🌐 Rutas disponibles

| Método | Endpoint                 | Roles permitidos          | Descripción                    |
|--------|--------------------------|----------------------------|--------------------------------|
| GET    | `/product/all`           | ADMIN, CLIENT, DELIVERER   | Obtener todos los productos    |
| GET    | `/product/search/{id}`   | ADMIN, CLIENT, DELIVERER   | Buscar un producto por ID      |
| DELETE | `/product/delete/{id}`   | ADMIN                      | Eliminar un producto por ID    |
| PUT    | `/product/edit/{id}`     | ADMIN                      | Editar un producto             |
| POST   | `/product/create`        | ADMIN                      | Crear un nuevo producto        |


**Base URL por defecto:** `http://localhost:8080`

---
## 🚀 Cómo ejecutar el microservicio con Docker

```bash
docker build -t producto-service .
docker run -p 8080:8080 producto-service
---

## 🛡️ Seguridad

> **Nota:** Este microservicio puede integrarse con un microservicio de autenticación para proteger sus endpoints.

- Los endpoints pueden estar protegidos con **JWT**, según la configuración global.
- El token debe enviarse en la cabecera:  
  `Authorization: Bearer <token>`
