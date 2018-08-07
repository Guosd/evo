FROM java:8
VOLUME ["/tmp", "/logs"]
ADD evo-config-1.0.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 20005
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=${config-type},${dockerfile-profile}","-Devo-appname=evo-config","-Xms512m","-Xmx512m","-Duser.timezone=GMT+08","-jar","/app.jar"]
