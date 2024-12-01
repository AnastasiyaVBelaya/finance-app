FROM eclipse-temurin:19-jre-focal
LABEL maintainer="ITAcademy"

ADD ./target/classifier-service-0.0.1-SNAPSHOT.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/classifier-service-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080 8089