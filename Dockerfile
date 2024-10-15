# Usar una imagen base de OpenJDK con JRE 17
FROM openjdk:21-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR de la aplicación generado por Maven a la imagen
COPY target/calendarapp-0.0.1-SNAPSHOT.jar /app/calendarapp.jar

# Exponer el puerto en el que la aplicación estará escuchando
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "/app/calendarapp.jar"]
