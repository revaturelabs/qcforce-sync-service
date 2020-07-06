FROM openjdk:8-jdk-alpine
ADD target/*.jar sync-service.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "sync-service.jar"]