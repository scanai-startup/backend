FROM openjdk:21-jdk
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar", "--matricula=123", "--email=teste@empresa.com", "--cpf=11111111111", "--nome=Admin", "--senha=abc123"]
