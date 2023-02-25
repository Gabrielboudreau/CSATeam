
# syntax=docker/dockerfile:1
FROM eclipse-temurin:16-alpine
WORKDIR /app
RUN apk update && apk upgrade && \
    apk add --no-cache git
RUN apk add freetype-dev
RUN apk add ttf-dejavu
RUN apk add fontconfig
COPY ["pom.xml", "mvnw", "./"]
COPY .mvn .mvn
RUN ./mvnw install -Dspring-boot.repackage.skip=true
COPY . .
RUN ./mvnw package
CMD ["java", "-jar", "target/spring-0.0.1-SNAPSHOT.jar"]
EXPOSE 8085
