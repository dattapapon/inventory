FROM eclipse-temurin:11-jdk-alpine
VOLUME /tmp
COPY target/*.jar flagship-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080