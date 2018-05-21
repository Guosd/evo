FROM java:8
VOLUME ["/tmp", "/logs"]
ENV QUERY_PORT=20015 \
	ES_HOSTS=http://192.168.1.16:9200 \
	STORAGE_TYPE=elasticsearch \
	RABBIT_ADDRESSES=192.168.1.20:5672 \
	RABBIT_VIRTUAL_HOST=vhost_eco \
	RABBIT_QUEUE=zipkin \
	RABBIT_USER=eco \
    RABBIT_PASSWORD=eco
ADD zipkin-server.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 20015
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Devo-appname=evo-zipkin","-jar","/app.jar"]
