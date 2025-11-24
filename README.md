# Demo Spring Boot Tasks

Aplicación de ejemplo construida con **Spring Boot 4 (snapshot)**, Java 21 y PostgreSQL. Expone un API REST para gestionar tareas con operaciones CRUD completas, DTOs validados y una arquitectura por capas (controller → service → repository).

```
┌─ Tecnologías ─────────────────────┐
│ ☕ Java 21                        │
│ ⚙️ Spring Boot (Web + Data JPA)   │
│ 🐘 PostgreSQL 17                  │
│ 🧰 Maven                          │
│ 🧱 Lombok + Bean Validation       │
└───────────────────────────────────┘
```

## 🧩 Características

- Entidad `Task` con auditoría automática (`createdAt`, `updatedAt`).
- Repositorio JPA con consultas derivadas (por estado, fecha y búsqueda por título).
- Servicio transaccional con DTO (`TaskCreationDto`) para controlar la entrada.
- Controlador REST (`TaskController`) con endpoints CRUD y filtros adicionales.
- Configuración de PostgreSQL en `application.properties`.
- `.cursorrules` para guiar contribuciones en Cursor.

## 🛠️ Prerrequisitos

- Java 21 (`sdkman` o `brew install openjdk@21`)
- Maven 3.9+ (`brew install maven`)
- PostgreSQL 17 (`brew install postgresql@17`)

## ⚙️ Configuración de base de datos

```sql
CREATE DATABASE demo;
CREATE USER luis WITH PASSWORD 'p4ssword';
GRANT ALL PRIVILEGES ON DATABASE demo TO luis;
```

Configura `src/main/resources/application.properties` si necesitas otros valores:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/demo
spring.datasource.username=luis
spring.datasource.password=p4ssword
spring.jpa.hibernate.ddl-auto=update
```

## 🚀 Ejecución

```bash
# Instalar dependencias y compilar
mvn clean install

# Ejecutar la aplicación
mvn spring-boot:run
```

La API quedará disponible en `http://localhost:8080`.

## 📡 Endpoints principales

| Método | Ruta                      | Descripción                           |
|--------|---------------------------|---------------------------------------|
| GET    | `/api/tasks`              | Lista todas las tareas                |
| GET    | `/api/tasks/{id}`         | Obtiene una tarea por ID              |
| POST   | `/api/tasks`              | Crea una nueva tarea                  |
| PUT    | `/api/tasks/{id}`         | Actualiza una tarea                   |
| DELETE | `/api/tasks/{id}`         | Elimina una tarea                     |
| GET    | `/api/tasks/completed`    | Lista tareas completadas              |
| GET    | `/api/tasks/incomplete`   | Lista tareas pendientes               |
| GET    | `/api/tasks/due-before`   | Filtra por fecha límite (`date`)      |
| GET    | `/api/tasks/search`       | Busca por título (`title`)            |

### Ejemplo de creación

```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
        "title": "Preparar demo",
        "description": "Configurar API y DB",
        "dueDate": "2025-01-05"
      }'
```

## 🧪 Próximos pasos sugeridos

- Añadir capa DTO de respuesta (ej. `TaskResponseDto`).
- Implementar pruebas unitarias/integración (`@SpringBootTest`, `MockMvc`).
- Manejar excepciones globales con `@ControllerAdvice`.
- Documentar la API con Springdoc OpenAPI.

---

¿Listo para extender la demo? Crea tus controladores/servicios en los paquetes ya configurados (`controller`, `service`, `repository`, etc.) y sigue las reglas definidas en `.cursorrules`. ¡Feliz código! 🎯

