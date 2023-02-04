FROM ubuntu:22.04 AS builder

WORKDIR /var/tmp/build
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN set -eux ;\
  apt-get update ;\
  apt-get install -y --no-install-recommends openjdk-11-jdk-headless ;\
  rm -rf /va/lib/apt/lists/* ;\
  ./mvnw clean package -DskipTests;

FROM ubuntu:22.04

RUN set -eux ;\
  apt-get update ;\
  apt-get install -y --no-install-recommends openjdk-11-jdk-headless ;\
  rm -rf /va/lib/apt/lists/* ;\
  mkdir -p /opt/ordering-system/lib;

COPY --from=builder /var/tmp/build/target /opt/ordering-system/lib
COPY --from=builder /var/tmp/build/target/ordering-system-1.0-SNAPSHOT.jar /opt/ordering-system
COPY config.yml /opt/ordering-system
COPY docker/docker-entry.sh /usr/local/bin/

ENV JAVA_OPTS "-server -Xms512M -Xmx512M -XX:+UseG1GC -XX:+UseStringDeduplication"

WORKDIR /opt/ordering-system

ENTRYPOINT [ "docker-entry.sh" ]

EXPOSE 8080 8081