FROM java:8
VOLUME /tmp
ADD evo-config-1.0.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 20005
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=subversion,sit","-jar","/app.jar"]
