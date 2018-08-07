FROM java:8
VOLUME ["/tmp", "/logs"]
ADD evo-sp-activiti-1.0.0-SNAPSHOT.jar app.jar
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN bash -c 'touch /app.jar'
EXPOSE 20125
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=${dockerfile-profile}","-Devo-appname=evo-sp-activiti","-Xms512m","-Xmx512m","-Duser.timezone=GMT+08","-jar","/app.jar"]
