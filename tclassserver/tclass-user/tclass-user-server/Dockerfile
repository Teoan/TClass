FROM adoptopenjdk/openjdk11:alpine
ARG JAR_FILE
EXPOSE 8003
ADD target/${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]