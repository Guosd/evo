FROM java:8
VOLUME ["/tmp", "/logs"]
ADD ${project.build.finalName}.${project.packaging} app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 20500
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=${dockerfile-profile}","-Devo-appname=${project.artifactId}","-Xms512m","-Xmx512m","-Duser.timezone=GMT+08","-jar","/app.jar"]
