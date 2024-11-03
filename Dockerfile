FROM openjdk:17-jdk-alpine
EXPOSE 8089
RUN apk add --no-cache curl
RUN curl -o /tp-foyer-5.0.0.jar -L "http://192.168.50.4:8081/repository/maven-releases/tn/esprit/tp-foyer/5.0.0/tp-foyer-5.0.0.jar"
ENTRYPOINT ["java","-jar","/tp-foyer-5.0.0.jar"]
