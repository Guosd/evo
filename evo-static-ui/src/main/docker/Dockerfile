FROM java:8
VOLUME ["/tmp", "/logs"]
ADD evo-static-ui-1.0.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 20500
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=${dockerfile-profile}","-Devo-appname=evo-static-ui","-Xms512m","-Xmx512m","-jar","/app.jar"]
