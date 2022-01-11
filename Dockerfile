FROM openjdk:8-jdk-alpine as release
WORKDIR /app
COPY target/redis-database-1.0.0.jar /app/app.jar
ENTRYPOINT ["java", "-jar"]
CMD ["/app/app.jar"]