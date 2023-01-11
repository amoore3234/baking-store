#!/bin/bash
exec /usr/bin/java ${JAVA_OPTS} -jar ordering-system.jar ${@: -server} config.yml