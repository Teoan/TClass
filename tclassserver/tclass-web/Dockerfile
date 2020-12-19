FROM java:latest
VOLUME ["/tmp","/home/root/tclass/upload"]
ADD target/tclass-web-1.0.0-BETA.jar app.jar
RUN bash -c "touch /app.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar","--java.security.egd=file:/dev/./urandom","--spring.datasource.url=jdbc:mysql://${MYSQL_URL}/tclass?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai","--spring.datasource.username=${MYSQL_USERNAME}","--spring.datasource.password=${MYSQL_PASSWORD}","--spring.redis.host=${REDIS_HOST}","--spring.redis.port=${REDIS_PORT}","--spring.redis.password=${REDIS_PASSWORD}"]

# sudo docker run  -d -p 127.0.0.1:8080:8080 -v ~/tclass/upload:/home/root/tclass/upload --name tclass -e MYSQL_URL=172.17.0.1:3306 -e MYSQL_USERNAME=Teoan -e MYSQL_PASSWORD=277077411 -e REDIS_HOST=172.17.0.2 -e REDIS_PORT=6379 -e REDIS_PASSWORD=277077411 teoan/tclass-web