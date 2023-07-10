FROM amazoncorretto:17-alpine
ARG JAR_FILE=build/*.jar
COPY ./build/libs/instagram-parody-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]