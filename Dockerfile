#Build environment (maven java jdk)
FROM maven AS build  
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn package spring-boot:repackage -DskipTests
#Runtime environment (java jre)
FROM openjdk:8-jre-alpine 
COPY --from=build  /usr/src/app/target/java-nurse-scheduling-problem-0.1.0.jar /usr/app/java-nurse-scheduling-problem-0.1.0.jar
EXPOSE 8080  
ENTRYPOINT ["java","-jar","/usr/app/java-nurse-scheduling-problem-0.1.0.jar"]

