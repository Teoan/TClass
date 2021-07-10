FROM adoptopenjdk/openjdk11:alpine
ARG JAR_FILE
EXPOSE 6666
ADD target/${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]