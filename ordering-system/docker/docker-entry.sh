#!/bin/bash
set -e

exec /usr/bin/java ${JAVA_OPTS} -jar ordering-system-1.0-SNAPSHOT.jar ${@:-server} config.yml
