FROM java:8
VOLUME /tmp
ADD evo-sp-org-1.0.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 50131
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=sit","-jar","/app.jar"]
