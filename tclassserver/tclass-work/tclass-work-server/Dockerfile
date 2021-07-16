FROM adoptopenjdk/openjdk11:alpine
ARG JAR_FILE
ARG SERVER_PORT
EXPOSE ${SERVER_PORT}
ADD target/${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-Xms64m -Xmx64m","-jar", "/app.jar"]