#Build environment (maven java jdk)
FROM maven AS build
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn package spring-boot:repackage -DskipTests
#Runtime environment (clean java jdk)
FROM openjdk:8
COPY --from=build  /usr/src/app/target/java-nurse-scheduling-problem-0.1.0.jar /usr/app/java-nurse-scheduling-problem-0.1.0.jar
ADD https://github.com/oliviercailloux/google-or-tools-java/raw/master/lib/libortools.so /usr/lib/
ADD https://github.com/oliviercailloux/google-or-tools-java/raw/master/lib/libjniortools.so /usr/lib/
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/java-nurse-scheduling-problem-0.1.0.jar"]

