# Gestión de Eventos

Este proyecto es una aplicación de gestión de eventos construida con Spring Boot, Spring MVC, Thymeleaf y Maven. Permite crear, gestionar y visualizar eventos en un calendario con opciones de filtrado y búsqueda.

## Características

- Crear, leer, actualizar y eliminar eventos (CRUD).
- Visualizar un calendario de eventos.
- Filtrar eventos por categoría, fecha, etc.
- Buscar eventos.

## Tecnologías Utilizadas

- Java 17
- Spring Boot 3.3.1
- Spring MVC
- Thymeleaf
- Hibernate
- PostgreSQL
- Maven
- IDE Eclipse
- Jira

## Requisitos Previos

- JDK 11 o superior
- Maven 3.6.0 o superior
- PostgreSQL 12 o superior

## Crear el Schema de la bd
- Instalar postgres 15 https://www.postgresql.org/download/windows/
- Crear bd con nombre "EventManagement"
- Modificar el archivo "application.properties"
- agregar usuario y password creada en la bd
- compilar


- En caso de no funcionar la compilacion de maven al crear el schema
- mvn liquibase:clearCheckSums
- mvn liquibase:update
## Configuración del Proyecto

### Clonar el Repositorio

```bash
git clone [https://github.com/tu-usuario/gestion-eventos.git](https://github.com/cristianjonhson/TD_Kibernum_Devops_Grupo2AmericanSummer-GestionEventos.git)
cd TD_Kibernum_Devops_Grupo2AmericanSummer-GestionEventos
