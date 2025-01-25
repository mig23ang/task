# Backend del Proyecto

## Descripción

Este proyecto contiene el backend de una aplicación de gestión de tareas. El backend está desarrollado en Java utilizando Spring Boot y se conecta a una base de datos PostgreSQL.

## Configuración del Docker Compose

El archivo `docker-compose.yaml` está configurado para levantar los servicios necesarios para el backend.

### Servicios

#### Backend

- **Puerto**: Mapea el puerto `8080` del contenedor al puerto `8080` del host. Esto permite acceder al servicio backend desde el host a través del puerto `8080`.
- **Variables de Entorno**:
  - `spring.datasource.url`: URL de la base de datos PostgreSQL.
  - `spring.datasource.username`: Nombre de usuario para acceder a la base de datos.
  - `spring.datasource.password`: Contraseña para acceder a la base de datos.
  - `spring.datasource.driver-class-name`: Clase del driver JDBC para PostgreSQL.
- **Dependencias**:
  - `database`: Indica que el servicio backend depende del servicio `database`. Docker Compose se asegurará de que el contenedor `database` esté iniciado antes de iniciar el contenedor backend.

### Razón de la Estructura

- **Modularidad**: Cada servicio (backend, database) está definido de manera modular, lo que facilita la gestión y el mantenimiento.
- **Configuración Centralizada**: Todas las configuraciones necesarias para ejecutar el servicio backend están centralizadas en el archivo `docker-compose.yaml`, lo que simplifica el despliegue.
- **Dependencias Claras**: La directiva `depends_on` asegura que las dependencias entre servicios se gestionen automáticamente, evitando problemas de arranque.
- **Portabilidad**: Al usar Docker, el entorno de ejecución es consistente en cualquier máquina, eliminando problemas de "funciona en mi máquina".

## Clases del Backend

### Controladores

- **TaskController**: Maneja las solicitudes HTTP relacionadas con las tareas. Permite crear, leer, actualizar y eliminar tareas.

### Servicios

- **TaskService**: Contiene la lógica de negocio para la gestión de tareas. Se comunica con el repositorio para realizar operaciones CRUD.

### Repositorios

- **TaskRepository**: Interfaz que extiende `JpaRepository` para realizar operaciones CRUD en la base de datos.

### Modelos

- **Task**: Clase que representa una tarea en el sistema. Contiene atributos como `id`, `title`, `description`, `status`, etc.

## Ejecución

Para levantar el backend, ejecuta el siguiente comando en el directorio raíz del proyecto:

```sh
docker-compose up
```

## Frontend
El frontend de la aplicación está diseñado para interactuar con una API RESTful que se ejecuta en http://localhost:8080. Las solicitudes definidas en el archivo Postman Collection (prueba.postman_collection.json) son ejemplos de cómo el frontend puede comunicarse con esta API para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre usuarios y tareas.

Solicitudes Definidas
Crear Usuarios (crear usuarios)

Método: POST
URL: http://localhost:8080/api/users
Cuerpo: JSON con los detalles del usuario a crear.
Descripción: Esta solicitud se utiliza para crear un nuevo usuario en el sistema.

Crear Tareas (crear tareas)

Método: POST
URL: http://localhost:8080/api/tasks
Cuerpo: JSON con los detalles de la tarea a crear.
Descripción: Esta solicitud se utiliza para crear una nueva tarea en el sistema.

Actualizar Tarea (actualizar tarea)

Método: PUT
URL: http://localhost:8080/api/tasks/1
Cuerpo: JSON con los detalles actualizados de la tarea.
Descripción: Esta solicitud se utiliza para actualizar una tarea existente en el sistema.

Consultar Todos los Usuarios (consultar todos los usuarios)

Método: GET
URL: http://localhost:8080/api/users/all
Descripción: Esta solicitud se utiliza para obtener una lista de todos los usuarios en el sistema.

Consultar Todas las Tareas (todas las tareas)

Método: GET
URL: http://localhost:8080/api/tasks/all
Descripción: Esta solicitud se utiliza para obtener una lista de todas las tareas en el sistema.

Consultar Tareas por Estado (todas las tareas por estado)

Método: GET
URL: http://localhost:8080/api/tasks/status/COMPLETED
Descripción: Esta solicitud se utiliza para obtener una lista de todas las tareas con un estado específico (en este caso, COMPLETED).

Consultar Tareas por Usuario (todas las tareas por usuario)

Método: GET
URL: http://localhost:8080/api/tasks/user/1
Descripción: Esta solicitud se utiliza para obtener una lista de todas las tareas asignadas a un usuario específico (en este caso, el usuario con ID 1).