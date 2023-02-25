
# syntax=docker/dockerfile:1
FROM balenalib/raspberry-pi-openjdk:16
WORKDIR /app
RUN apt update && apk upgrade && \
    apt install git
RUN apt install freetype-dev
RUN apt install ttf-dejavu
RUN apt install fontconfig
COPY ["pom.xml", "mvnw", "./"]
COPY .mvn .mvn
RUN ./mvnw install -Dspring-boot.repackage.skip=true
COPY . .
RUN ./mvnw package
CMD ["java", "-jar", "target/spring-0.0.1-SNAPSHOT.jar"]
EXPOSE 8085
