# Usar una imagen base de Maven para la construcción
FROM maven:3.9.6 AS builder

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo pom.xml y el directorio de código fuente al contenedor
COPY pom.xml ./
COPY src ./src

# Ejecutar el análisis de SonarQube y construir la aplicación
RUN mvn clean verify sonar:sonar -Dsonar.host.url=http://host.docker.internal:9000 -Dsonar.login=sqp_fc460ee7637d88918967791ee63e485c3852a112 -e -X


# Fase final: Usar una imagen base de OpenJDK con JRE 17
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Crear el directorio de logs
RUN mkdir -p /var/log/app_logs

# Copiar el archivo JAR de la aplicación generado por Maven a la imagen
COPY --from=builder /app/target/calendarapp-0.0.1-SNAPSHOT.jar /app/calendarapp.jar

# Exponer el puerto en el que la aplicación estará escuchando
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "/app/calendarapp.jar"]
