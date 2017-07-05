#!/bin/sh

KAFKA_TOPIC="${KAFKA_TOPIC:-brandenburg}"

java -Dkafka.metadata-broker-list=${KAFKA_BROKER_LIST} -Dkafka.bootstrap-servers=${KAFKA_BOOTSTRAP_SERVERS} -Dkafka.topic=${KAFKA_TOPIC} -jar app.jar