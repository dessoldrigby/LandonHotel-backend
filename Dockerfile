FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
EXPOSE 8080
#ARG JAR_FILE=
ADD target/spring-learning-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar" ]