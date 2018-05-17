FROM java:8
VOLUME ["/tmp", "/logs"]
ADD evo-sp-sms-1.0.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 20030
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=sit","-jar","/app.jar"]
