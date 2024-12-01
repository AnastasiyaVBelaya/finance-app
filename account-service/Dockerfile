FROM eclipse-temurin:19-jre-focal
LABEL maintainer="ITAcademy"

ADD ./target/account-service-0.0.1-SNAPSHOT.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/account-service-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080 8089