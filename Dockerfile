
# syntax=docker/dockerfile:1
FROM eclipse-temurin:16-jdk
WORKDIR /app
RUN apt update && \
    apt install git
RUN apt install libfreetype6-dev
RUN apt install ttf-dejavu
RUN apt install fontconfig
COPY ["pom.xml", "mvnw", "./"]
COPY .mvn .mvn
RUN ./mvnw install -Dspring-boot.repackage.skip=true
COPY . .
RUN ./mvnw package
CMD ["java", "-jar", "target/spring-0.0.1-SNAPSHOT.jar"]
EXPOSE 8085
