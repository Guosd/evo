FROM java:8
VOLUME ["/tmp", "/logs"]
ADD ${project.build.finalName}.${project.packaging} app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 20000
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=${profiles-active}","-Devo-appname=${project.artifactId}","-Xms256m","-Xmx256m","-Duser.timezone=GMT+08","-jar","/app.jar"]
