FROM openjdk:8
EXPOSE 8080
ADD ./manageUser/target/*.jar manage_user.jar
ENTRYPOINT ["java","-jar","/manage_user.jar"]
