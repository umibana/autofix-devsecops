FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} autofix.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/autofix.jar"]