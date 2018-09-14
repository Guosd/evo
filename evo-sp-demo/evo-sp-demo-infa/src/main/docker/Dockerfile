FROM java:8
VOLUME ["/tmp", "/logs"]
ADD evo-sp-demo-infa-2.5.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 30004
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=${dockerfile-profile}","-Devo-appname=evo-sp-demo-infa","-Xms512m","-Xmx512m","-Duser.timezone=GMT+08","-jar","/app.jar"]
