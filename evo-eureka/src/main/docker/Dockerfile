FROM java:8
VOLUME ["/tmp", "/logs"]
ADD evo-eureka-1.0.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 50101
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=${dockerfile-profile}","-Devo-appname=evo-eureka","-Xms256m","-Xmx256m","-Duser.timezone=GMT+08","-jar","/app.jar"]
