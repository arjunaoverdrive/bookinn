FROM eclipse-temurin:21
WORKDIR /app
COPY build/libs/bookinn-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]