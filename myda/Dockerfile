FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER ccQi

RUN mkdir -p /myda/server/logs \
    /myda/server/temp \
    /myda/skywalking/agent

WORKDIR /myda/server

ENV SERVER_PORT=8080

EXPOSE ${SERVER_PORT}

ADD ./target/myda.jar ./app.jar

ENTRYPOINT ["java", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-Dserver.port=${SERVER_PORT}", \
            # 应用名称 如果想区分集群节点监控 改成不同的名称即可
#            "-Dskywalking.agent.service_name=daily-server", \
#            "-javaagent:/daily/skywalking/agent/skywalking-agent.jar", \
            "-jar", "app.jar"]
