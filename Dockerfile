FROM openjdk:21-ea-24-oracle
WORKDIR /app
COPY target/reserva_citas_medicas-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]