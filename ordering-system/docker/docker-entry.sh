#!/bin/bash
set -e
if ! [ -z ${AUTO_MIGRATE+x} ]; then
  /usr/bin/java ${JAVA_OPTS} -jar ordering-system-1.0-SNAPSHOT.jar db migrate config.yml
fi
exec /usr/bin/java ${JAVA_OPTS} -jar ordering-system-1.0-SNAPSHOT.jar ${@:-server} config.yml