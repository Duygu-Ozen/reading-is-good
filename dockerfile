FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} readingIsGood-0.0.1.jar
ENTRYPOINT ["java","-jar","/readingIsGood-0.0.1.jar"]