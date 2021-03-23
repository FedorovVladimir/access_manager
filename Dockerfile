FROM openjdk:14-alpine
COPY /build/libs/access_manager-*-all.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
