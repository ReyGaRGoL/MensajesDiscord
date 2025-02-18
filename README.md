# 📅 API de Programación de Mensajes en Discord con Spring Boot

Este proyecto es una API desarrollada con Java y Spring Boot que permite programar mensajes para ser enviados a Discord en una fecha y hora establecida por el usuario.

---

## 🎯 Propósito del Proyecto

El objetivo de esta aplicación es:

- Desarrollar una API RESTful con Spring Boot.
- Permitir a los usuarios programar mensajes para ser enviados a un canal de Discord en un momento determinado.
- Integrar la API de Discord para el envío automatizado de mensajes.
- Configurar variables de entorno en el sistema de manera manual.
- Mostrar información en una interfaz web con Thymeleaf.
- Seguir buenas prácticas en la estructuración del código y la configuración del sistema.

---

## 🛠️ Funcionalidades Principales

1️⃣ **Programar mensajes**: Permite a los usuarios establecer el contenido de un mensaje y la fecha/hora en la que debe enviarse a un canal de Discord.

2️⃣ **Listar mensajes programados**: Muestra los mensajes que están pendientes de ser enviados.

3️⃣ **Cancelar mensajes programados**: Opción para eliminar un mensaje antes de su envío.

4️⃣ **Interfaz web con Thymeleaf**: Se usa una vista en HTML renderizada con Thymeleaf para visualizar y gestionar los mensajes programados.

5️⃣ **Configuración mediante variables de entorno**: Se configuran directamente en el sistema operativo sin necesidad de archivos externos.

---

## 🧩 Tecnologías Utilizadas

- **Java**: Lenguaje principal del desarrollo.
- **Spring Boot**: Framework para agilizar el desarrollo backend.
- **Thymeleaf**: Motor de plantillas para renderizar la interfaz web.
- **Maven**: Para la gestión de dependencias.
- **Discord API**: Para el envío de mensajes automatizados.
- **PostgreSQL**: Base de datos utilizada para almacenar los mensajes programados.
- **Flyway**: Para el control de versiones y migraciones de la base de datos.
- **JPA/Hibernate**: Para gestionar la persistencia y consultas a la base de datos.
- **Swagger**: Para documentar y visualizar la API de manera interactiva.

---

## 📦 Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener:

- **Java 17 o superior**.
- **Maven** instalado.
- **Un bot de Discord creado** y su token generado.
- **PostgreSQL instalado y configurado**.
- **Las variables de entorno configuradas en el sistema**.

---

## 🚀 Cómo Usarlo

1️⃣ **Clonar el repositorio:**

```bash
git clone https://github.com/tu-usuario/tu-repositorio.git
```

2️⃣ **Configurar la Base de Datos:**
--Asegúrate de que PostgreSQL esté instalado y en ejecución.

--Crea una base de datos para el proyecto en PostgreSQL. Puedes hacerlo con el siguiente comando (ajustando el nombre de la base de datos):

--sql
--Copiar
--Editar
--CREATE DATABASE nombre_de_tu_base_de_datos;
**Nota:** Este paso es crucial ya que la aplicación necesita una base de datos para almacenar los mensajes programados.

3️⃣ **Configurar las Variables de Entorno:**
--Debes configurar las siguientes variables de entorno en tu sistema operativo antes de ejecutar el programa:

--En Windows:

--Abre el menú de inicio y busca "Variables de entorno".
--En la sección de variables de usuario, agrega las siguientes variables:
--DB_NAME: Nombre de la base de datos en PostgreSQL.
--DB_USER: Usuario de la base de datos.
--DB_PASSWORD: Contraseña de la base de datos.
--BOT_TOKEN: Token del bot de Discord.
En Linux/Mac:

-Abre una terminal y ejecuta:

bash
Copiar
Editar
export DB_NAME=nombre_de_tu_base_de_datos
export DB_USER=tu_usuario
export DB_PASSWORD=tu_contraseña
export BOT_TOKEN=tu_token_de_discord
Para hacer permanentes los cambios, agrega estas líneas al archivo ~/.bashrc o ~/.zshrc (dependiendo del shell que uses).

Asegúrate de que las variables estén correctamente configuradas antes de continuar. Esto es esencial para que la aplicación pueda acceder a la base de datos y al bot de Discord.



4️⃣ **Compilar y ejecutar la API:**

```bash
mvn clean install  
mvn spring-boot:run  
```

5️⃣ **Usar la aplicación**

- **Acceder a la interfaz web**

Abre un navegador y accede a:

```
http://localhost:8080
```

Aquí podrás visualizar los mensajes programados y gestionar su envío.

- **Consultar la documentación de la API**

Swagger genera documentación interactiva para la API en:

```
http://localhost:8080/swagger-ui.html
```

Aquí puedes probar las rutas y ver detalles sobre los endpoints disponibles.

- **Programar un mensaje mediante API**

Usa una herramienta como Postman o cURL para hacer una solicitud `POST` a la API con el contenido del mensaje y la fecha/hora de envío.

---

## 🎉 Notas Finales

Este proyecto es parte de mi aprendizaje en desarrollo backend y automatización con Discord. Si deseas contribuir o mejorar la funcionalidad, siéntete libre de hacerlo. 🚀

¡Gracias por leer y feliz codificación! 💻

