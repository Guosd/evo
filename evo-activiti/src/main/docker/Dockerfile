FROM java:8
VOLUME ["/tmp", "/logs"]
ADD evo-activiti-1.0.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 20025
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=${dockerfile-profile}","-Devo-appname=evo-activiti","-jar","/app.jar"]
