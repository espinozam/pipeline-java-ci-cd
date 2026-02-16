FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml ./
COPY src ./src
COPY checkstyle.xml ./
RUN mvn -B -DskipTests clean package

FROM tomcat:10.1-jdk21-temurin
COPY --from=build /app/target/pipeline-java-ci-cd-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
