FROM openjdk:8
ADD target/project-0.0.1-SNAPSHOT.jar egen-clear-sky.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","egen-clear-sky.jar"]
