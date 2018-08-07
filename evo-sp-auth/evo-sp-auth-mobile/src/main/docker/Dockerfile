FROM java:8
VOLUME ["/tmp", "/logs"]
ADD evo-sp-auth-mobile-1.0.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 20115
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=${dockerfile-profile}","-Devo-appname=evo-sp-auth-mobile","-Xms512m","-Xmx512m","-Duser.timezone=GMT+08","-jar","/app.jar"]
