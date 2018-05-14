FROM java:8
VOLUME /logs
ADD evo-sp-auth-mobile-1.0.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 20115
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=sit","-jar","/app.jar"]
