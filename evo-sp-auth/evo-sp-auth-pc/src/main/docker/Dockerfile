FROM java:8
VOLUME ["/tmp", "/logs"]
ADD evo-sp-auth-pc-1.0.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 20110
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=${dockerfile-profile}","-Devo-appname=evo-sp-auth-pc","-jar","/app.jar"]
