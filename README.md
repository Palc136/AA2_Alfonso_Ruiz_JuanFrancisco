=======================================================================
           SISTEMA DE GESTIÓN CINEMATOGRÁFICA - CINEPLUS
=======================================================================

Este proyecto es una aplicación Web Full-Stack desarrollada para la 
gestión y visualización de un catálogo de películas, integrando una 
base de datos relacional, un backend en Java y un frontend en Angular.

ESPECIFICACIONES TÉCNICAS Y ARQUITECTURA
-----------------------------------------------------------------------

ESTRUCTURA DEL PROYECTO (Patrón de Diseño):
* Arquitectura Multicapa (Layered Architecture):
  - Persistence Layer: Uso de JPA/Hibernate para el mapeo objeto-relacional.
  - Service/Servlet Layer: Controladores en Java para gestionar peticiones HTTP.
  - Data Transfer: Uso de objetos DTO y Serialización para el envío de datos.
  - Frontend Layer: Arquitectura basada en Componentes y Servicios de Angular.

TECNOLOGÍAS Y LIBRERÍAS UTILIZADAS:

  BACKEND (Java Jakarta EE):
  * JPA / Hibernate: Para la gestión de la base de datos sin escribir SQL manual en el código.
  * GSON (Google): Librería principal para convertir objetos Java a formato JSON.
  * Jakarta Servlet API: Para la creación de los Endpoints de la API.
  * Driver Connector J: Para la comunicación física entre Java y MySQL.

  FRONTEND (Angular):
  * HttpClientModule: Para realizar peticiones asíncronas al Backend.
  * AppRoutingModule: Gestión de rutas para navegación entre la lista y los detalles.
  * TypeScript: Lenguaje base para una programación tipada y segura.

-----------------------------------------------------------------------
1. REQUISITOS PREVIOS (Software a instalar)
-----------------------------------------------------------------------
Antes de iniciar, asegúrate de tener instalados los siguientes programas:

* Java Development Kit (JDK) 17 o superior.
* Apache Tomcat 9.0 (Servidor de aplicaciones).
* MySQL Server 8.0 y MySQL Workbench (o phpMyAdmin).
* Node.js (versión LTS) y Angular CLI (instalar con: npm install -g @angular/cli).
* IDEs: NetBeans (para el Backend) y VS Code (para el Frontend).

-----------------------------------------------------------------------
2. PASOS PARA EL MONTAJE E INICIO DEL PROYECTO
-----------------------------------------------------------------------

PASO A: IMPORTACIÓN DE LA BASE DE DATOS
1. Abre tu gestor de MySQL.
2. opcion1: 
3. Crea la base de datos: CREATE DATABASE cineplus_db;
4. Selecciona la base de datos: USE cineplus_db;
5. pega la consulta sql del archivo "database-script.sql"
6. opcion 2:
7. Importa y ejecuta el archivo "database-script.sql" incluido en 
   el proyecto. Este creará las tablas (Actor, peliculas, etc.) 
   se insertarán los datos iniciales.

PASO B: CONFIGURACIÓN Y EJECUCIÓN DEL BACKEND (NetBeans)
1. Abre el proyecto backend en NetBeans u otra erramienta que pueda ejecutara aplicacion web java.
2. Ve a src/main/resources/META-INF/persistence.xml y verifica que el 
   usuario y contraseña de MySQL sean los correctos para tu sistema.
3. Haz clic derecho sobre el nombre del proyecto y selecciona 
   "Clean and Build".
4. Haz clic derecho y selecciona "Run". El servidor Tomcat iniciará.
5. Verifica el JSON en el navegador: http://localhost:8080/[tu-proyecto](juanAlfonsomi-backend)/api/movies

PASO C: EJECUCIÓN DEL FRONTEND (Terminal)
1. Abre una terminal en la carpeta raíz del proyecto frontend.
2. Ejecuta: npm install (para descargar las dependencias).
3. Ejecuta: ng serve (para iniciar el servidor de desarrollo).
4. Abre el navegador en: http://localhost:4200

-----------------------------------------------------------------------
3. LOGROS ALCANZADOS EN ESTA ETAPA
-----------------------------------------------------------------------
* CONEXIÓN END-TO-END: Se logró integrar exitosamente la base de datos 
  MySQL con el servidor Java Jakarta EE y el cliente Angular.
* PERSISTENCIA CON JPA: Implementación correcta de Hibernate para el 
  mapeo de entidades (clase Actor <-> tabla Actor).
* DESPLIEGUE DINÁMICO: 
  - Los datos de la DB se reflejan en el componente "Movie Cards" 
    (Catálogo general).
  - El componente "Movie Details" consume la API para mostrar 
    sinopsis, portadas y el reparto (Cast) de forma dinámica.
* SERIALIZACIÓN JSON: Configuración de GSON para entregar las 
  fechas y objetos relacionados correctamente a través de Tomcat.

-----------------------------------------------------------------------
4. PUERTOS Y ENDPOINTS
-----------------------------------------------------------------------
* Frontend (Angular): http://localhost:4200
* Backend (Tomcat): http://localhost:8080
* API de Películas: http://localhost:8080/[nombre](juanAlfonsomi-backend)/api/movies
* API de Reparto: http://localhost:8080/[nombre](juanAlfonsomi-backend)/api/cast?movieId=1

-----------------------------------------------------------------------
5. PENDIENTES Y MEJORAS FUTURAS
-----------------------------------------------------------------------
* Implementar el sistema de Autenticación de Usuarios (Registro/Login).
* Desarrollar la lógica de "Favoritos" para que los usuarios guarden 
  sus películas preferidas.
* Habilitar la sección de "Comentarios" vinculada a los triggers de 
  la base de datos para actualizar el rating automáticamente.
* Mejorar el buscador para filtrado por género y año.

