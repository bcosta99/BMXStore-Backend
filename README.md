# BMXStore eCommerce

Este es un proyecto de eCommerce desarrollado en Java utilizando el framework Spring Boot. El objetivo de este proyecto es aprender distintas tecnologías para la creación y gestión de una tienda en línea.

## Funcionalidades Principales

- **Gestión de Productos**: Permite agregar, editar y eliminar productos.
- **Carrito de Compras**: Los usuarios pueden agregar productos a su carrito y realizar compras.
- **Autenticación y Autorización**: Utiliza Spring Security y tokens JWT para gestionar la autenticación y autorización de usuarios.
- **Persistencia de Datos**: Utiliza Spring Data JPA para interactuar con la base de datos.
- **Pagos**: (Pendiente) Integración de pasarela de pago.
- **Envío**: (Pendiente) Cálculo de costos de envío.

## Requisitos

- Java 17
- [Spring Boot 3.0.6](https://spring.io/projects/spring-boot)
- [Spring Data](https://spring.io/projects/spring-data-jpa)
- [Spring Security](https://spring.io/projects/spring-security)
- JUnit 5
- Mockito
- Maven
- SQLite

## Instalación

1. Clona este repositorio en tu máquina local:

```bash
git clone https://github.com/bcosta99/BMXStore-Backend.git
```
2. Abre el proyecto en tu IDE favorito


3. Este proyecto usa SQLite como base de datos embebida.
   1. (opcional) Puedes configurar una base de datos dedicada en el archivo application.properties o application.yml:

```yaml
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=usuario
spring.datasource.password=contraseña
```
4. Ejecuta la aplicación:

```bash
./mvnw spring-boot:run
```
### Uso

Una vez que la aplicación esté en funcionamiento, puedes acceder a la tienda en línea desde tu navegador. Asegúrate de haber creado una cuenta de usuario y de haber iniciado sesión para acceder a todas las funcionalidades.
