FROM openjdk:11
ADD target/herd-service.jar herd-service.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "herd-service.jar"]