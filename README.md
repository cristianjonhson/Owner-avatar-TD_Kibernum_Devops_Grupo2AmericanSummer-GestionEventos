Aquí tienes una versión actualizada del README que incluye la información sobre el despliegue de contenedores con Jenkins y los comandos para realizar contenedores locales. He organizado la información para facilitar la comprensión y el uso.

---

# Gestión de Eventos

Este proyecto es una aplicación de gestión de eventos construida con Spring Boot, Spring MVC, Thymeleaf y Maven. Permite crear, gestionar y visualizar eventos en un calendario con opciones de filtrado y búsqueda.

## Características

- Crear, leer, actualizar y eliminar eventos (CRUD).
- Visualizar un calendario de eventos.
- Filtrar eventos por categoría, fecha, etc.
- Buscar eventos.

## Tecnologías Utilizadas

- Java 21
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

## Configuración del Proyecto

### Clonar el Repositorio

```bash
git clone https://github.com/cristianjonhson/TD_Kibernum_Devops_Grupo2AmericanSummer-GestionEventos.git
cd TD_Kibernum_Devops_Grupo2AmericanSummer-GestionEventos
```

## Despliegue de Contenedores con Jenkins

A continuación, se detalla el Jenkinsfile utilizado para la construcción y despliegue del proyecto en contenedores Docker:

```groovy
def COLOR_MAP = [
  'SUCCESS': 'good',
  'FAILURE':'danger',
]

pipeline {
    agent any
    
    environment {
        // Maven and JDK tools defined in Jenkins
        MAVEN_HOME = tool 'apache-maven-3.9.8'
        JAVA_HOME = tool 'jdk 21'
        PATH = "${MAVEN_HOME}/bin:${JAVA_HOME}/bin:${env.PATH}"

        // Docker environment
        NETWORK_NAME = 'gestion_eventos_network'
        PG_CONTAINER = 'pg_container'
        APP_IMAGE = 'gestion_eventos_app_image'
        APP_CONTAINER = 'gestion_eventos_app'
        POSTGRES_USER = 'postgres'
        POSTGRES_PASSWORD = 'admin'
        POSTGRES_DB = 'gestion_eventos'
        SPRING_DATASOURCE_URL = "jdbc:postgresql://pg_container:5432/gestion_eventos"
        SPRING_DATASOURCE_USERNAME = 'postgres'
        SPRING_DATASOURCE_PASSWORD = 'admin'
        SPRING_DATASOURCE_DRIVER_CLASS_NAME = 'org.postgresql.Driver'
        SPRING_JPA_DATABASE_PLATFORM = 'org.hibernate.dialect.PostgreSQLDialect'
        SERVER_PORT = '8082'
        SPRING_APPLICATION_NAME = 'G2-GestionEventos'
        SONARQUBE_URL = 'http://localhost:9000'
        scannerHome = tool 'SonarQubeScanner'
    }
    
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/cristianjonhson/TD_Kibernum_Devops_Grupo2AmericanSummer-GestionEventos.git', branch: 'feature/PT2-45-JM'
            }
        }

        stage('Build Project with Maven') {
            steps {
                sh 'mvn -f pom.xml clean install'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube-10.6.0.92116') {
                    sh '${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=Grupo2AmericanSummer-GestionEventos -Dsonar.host.url=${SONARQUBE_URL} -Dsonar.java.binaries=target/classes'
                }
            }
        }

        stage('Run Unit Tests') {
            steps {
                sh 'mvn -f pom.xml test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Create Docker Network') {
            steps {
                script {
                    sh 'docker network create ${NETWORK_NAME} || echo "Network already exists"'
                }
            }
        }

        stage('Start PostgreSQL Container') {
            steps {
                script {
                    sh '''
                    docker run -d --name ${PG_CONTAINER} --network ${NETWORK_NAME} \
                        -e POSTGRES_USER=${POSTGRES_USER} \
                        -e POSTGRES_PASSWORD=${POSTGRES_PASSWORD} \
                        -e POSTGRES_DB=${POSTGRES_DB} \
                        -p 5432:5432 postgres:latest
                    '''
                }
            }
        }

        stage('Build Application Docker Image') {
            steps {
                script {
                    sh 'docker build -t ${APP_IMAGE} .'
                }
            }
        }

        stage('Start Application Container') {
            steps {
                script {
                    sh '''
                    docker run -d --name ${APP_CONTAINER} --network ${NETWORK_NAME} \
                        -e "SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL}" \
                        -e "SPRING_DATASOURCE_USERNAME=${SPRING_DATASOURCE_USERNAME}" \
                        -e "SPRING_DATASOURCE_PASSWORD=${SPRING_DATASOURCE_PASSWORD}" \
                        -e "SPRING_DATASOURCE_DRIVER_CLASS_NAME=${SPRING_DATASOURCE_DRIVER_CLASS_NAME}" \
                        -e "SPRING_JPA_DATABASE_PLATFORM=${SPRING_JPA_DATABASE_PLATFORM}" \
                        -e "SERVER_PORT=${SERVER_PORT}" \
                        -e "SPRING_APPLICATION_NAME=${SPRING_APPLICATION_NAME}" \
                        -p ${SERVER_PORT}:${SERVER_PORT} ${APP_IMAGE}
                    '''
                }
            }
        }

        stage('Inspect Docker Network') {
            steps {
                script {
                    sh 'docker network inspect ${NETWORK_NAME}'
                }
            }
        }

        stage('Run SQL Script') {
            steps {
                script {
                    sh '''
                    docker exec -i ${PG_CONTAINER} psql -U ${POSTGRES_USER} -d ${POSTGRES_DB} <<EOF
                    CREATE DATABASE gestion_eventos WITH ENCODING 'UTF8';
                    -- Restante de la creación de tablas e inserción de datos aquí...
                    EOF
                    '''
                }
            }
        }
    }

    post {
        always {
            echo 'Slack Notification desde Jenkinsfile'
            slackSend channel: 'jenkins',
            color: COLOR_MAP[currentBuild.currentResult],
            message: "*${currentBuild.currentResult}: Job ${env.JOB_NAME} - build ${env.BUILD_NUMBER}\n More Info at: ${env.BUILD_URL}"
        }
    }
}
```

## Despliegue Local de Contenedores

Si prefieres ejecutar los contenedores de forma local, puedes utilizar los siguientes comandos:

1. Crear una red de Docker:

```bash
docker network create gestion_eventos_network
```

2. Iniciar el contenedor de PostgreSQL:

```bash
docker run -d --name pg_container --network gestion_eventos_network -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=gestion_eventos -p 5432:5432 postgres:latest
```

3. Construir la imagen de la aplicación:

```bash
docker build -t gestion_eventos_app_image .
```

4. Iniciar el contenedor de la aplicación:

```bash
docker run -d --name gestion_eventos_app --network gestion_eventos_network -e "SPRING_DATASOURCE_URL=jdbc:postgresql://pg_container:5432/gestion_eventos" -e "SPRING_DATASOURCE_USERNAME=postgres" -e "SPRING_DATASOURCE_PASSWORD=admin" -e "SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver" -e "SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.PostgreSQLDialect" -e "SERVER_PORT=8082" -e "SPRING_APPLICATION_NAME=G2-GestionEventos" -p 8082:8082 gestion_eventos_app_image
```

5. Inspeccionar la red de Docker:

```bash
docker network inspect gestion_eventos_network
```

6. Para ejecutar un script SQL, puedes copiarlo al contenedor y conectarte a la base de datos:

```bash
docker cp C:\ruta\del\script.sql pg_container:/script.sql
docker exec -it pg_container bash
psql -U postgres -d gestion_eventos -f /script.sql
```

---

Asegúrate de modificar las rutas y credenciales según tu entorno local. ¡Espero que esta información te sea útil!
