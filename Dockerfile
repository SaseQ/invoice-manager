FROM openjdk:11 as build
ADD target/invoice-manager-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-Dspring.profiles.active-prod", "-jar", "invoice-manager-0.0.1-SNAPSHOT.jar"]