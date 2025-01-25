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

