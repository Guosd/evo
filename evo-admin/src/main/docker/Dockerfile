FROM java:8
VOLUME ["/tmp", "/logs"]
ADD evo-admin-1.0.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 20020
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=${dockerfile-profile}","-Devo-appname=evo-admin","-Xms256m","-Xmx256m","-jar","/app.jar"]
