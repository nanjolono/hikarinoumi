FROM 172.30.50.212/poc/sunyard-jdk
ENV SH_PATH=/usr/local/bin
ENV PROFILES=dev
VOLUME /tmp
ADD target/user-0.0.1-SNAPSHOT.jar app.jar
COPY docker-entrypoint.sh $SH_PATH/docker-entrypoint.sh
EXPOSE 8082
EXPOSE 9100
RUN chmod +x $SH_PATH/docker-entrypoint.sh
ENTRYPOINT ["docker-entrypoint.sh"]