FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/desafio-picpay-transacao-0.0.1-SNAPSHOT.jar /app/desafio-picpay-transacao.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/desafio-picpay-transacao.jar"]