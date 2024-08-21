# Proyecto Logística


## Descripción del Proyecto

Este proyecto de logística está dividido en tres módulos principales:

- **Delivery**: Maneja todo lo relacionado con las entregas y envíos.
- **Providers**: Gestiona la información de los proveedores.
- **Terminals**: Administra los datos de las terminales.

El proyecto incluye varias entidades que están relacionadas entre sí, lo que requiere un orden específico al cargar los datos para evitar conflictos.


## Pasos para Cargar las Tablas

A continuación se detalla el orden en el que se deben ejecutar los endpoints para cargar los datos en las tablas relacionadas:

1. **Providers**:
   - Comienza creando los proveedores, ya que otras entidades pueden depender de estos datos.
   - Endpoint: `POST /providers`

2. **TerminalState**:
   - Luego, se cargan los estados de las terminales, que son independientes y pueden ser referenciados por las terminales.
   - Endpoint: `POST /terminal-states`

3. **TerminalModels**:
   - A continuación, se crean los modelos de terminales, que dependen de los estados de las terminales.
   - Endpoint: `POST /api/terminals/create`
   - Endpoint: `POST /api/terminals/state`

4. **Commerce**:
   - Después, se crean los registros de comercios, que pueden estar relacionados con proveedores.
   - Endpoint: `POST /commerce`

5. **Reserve**:
   - Luego, se deben cargar las reservas, que pueden depender de proveedores y comercios.
   - Endpoint: `POST /reserves`

6. **PurchaseOrder**:
   - A continuación, se crean las órdenes de compra, que pueden estar asociadas a reservas, proveedores y comercios.
   - Endpoint: `POST /purchase-orders`

7. **Delivery**:
   - Finalmente, se registran las entregas, que pueden estar relacionadas con órdenes de compra y otros datos anteriores.
   - Endpoint: `POST /deliveries`


## Acceso a Swagger

Para acceder a la interfaz de Swagger y probar los endpoints en un entorno local, utiliza la siguiente URL:

- **Swagger UI URL:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)


## Credenciales de la Base de Datos H2 en Local

Para realizar pruebas con la base de datos en memoria H2, utiliza las siguientes credenciales:

- **H2 Console URL:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **User Name:** `prueba`
- **Password:** `password`