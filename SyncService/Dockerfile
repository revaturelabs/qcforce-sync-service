FROM openjdk:8-jdk-alpine
ADD target/*.jar sync-service.jar
ADD src/main/resources/*.p12 src/main/resourses/projectsync-281422-0ea6cec11520.p12
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "sync-service.jar"]