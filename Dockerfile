FROM openjdk:8-jdk-alpine


RUN echo "great echo"
ADD target/authorization-server-0.0.1-SNAPSHOT.jar authorization-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/authorization-server-0.0.1-SNAPSHOT.jar"]