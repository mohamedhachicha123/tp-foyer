FROM openjdk:17-jdk-alpine
EXPOSE 8089
ADD target/tp-foyer-5.1.8.jar tp-foyer-5.1.8.jar
ENTRYPOINT ["java","-jar","/tp-foyer-5.1.8.jar"]