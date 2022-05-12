FROM gradle:7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle shadowJar --no-daemon

FROM openjdk:11
EXPOSE 8080:8080
RUN mkdir /app
ENV DB_URL jdbc:mysql://db:3306/ktor
ENV DB_USER user
ENV DB_DRIVER com.mysql.cj.jdbc.Driver
ENV DB_PASSWORD mypass
COPY --from=build /home/gradle/src/build/libs/*.jar /app/ktor-practice.jar
ENTRYPOINT ["java","-jar","/app/ktor-practice.jar"]