#!/bin/bash
set -e
if ! [ -z ${AUTO_MIGRATE+x} ]; then
  /usr/bin/java ${JAVA_OPTS} -jar circuit-mgmt.jar db migrate config.yml
fi
exec /usr/bin/java ${JAVA_OPTS} -jar ordering-system.jar ${@: -server} config.yml