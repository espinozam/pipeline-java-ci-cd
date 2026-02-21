# Usa la imagen de Maven para compilar el proyecto y generar el archivo WAR
FROM maven:3.9.9-eclipse-temurin-21 AS build

# Establece el directorio de trabajo dentro del contenedor
# Copia el archivo pom.xml y el código fuente al contenedor
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Compila el proyecto y genera el archivo WAR, omitiendo las pruebas
RUN mvn -q -DskipTests package

# Usa la imagen de Tomcat para ejecutar la aplicación web Java
FROM tomcat:10.1-jdk21-temurin

# Copia el archivo WAR generado al directorio de aplicaciones de Tomcat
COPY --from=build /app/target/pipeline-java-ci-cd-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
