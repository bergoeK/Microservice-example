FROM openjdk:11
#LABEL maintainer="pateluday07@gmail.com"


COPY /target/*.jar app.jar

EXPOSE 8085

ENTRYPOINT ["java","-jar","app.jar"]