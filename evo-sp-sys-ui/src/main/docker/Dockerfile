FROM java:8
VOLUME ["/tmp", "/logs"]
ADD evo-sp-sys-ui-1.0.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 20505
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=${dockerfile-profile}","-Devo-appname=evo-sp-sys-ui","-Xms512m","-Xmx512m","-jar","/app.jar"]
