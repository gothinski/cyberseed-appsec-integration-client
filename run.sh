#!/bin/bash
LOCATION1="./sageIntegrationClient.jar"
LOCATION2="./target/sageIntegrationClient.jar"
if [ -f "$LOCATION1" ]
then
    java -jar $LOCATION1
else
    java -jar $LOCATION2
fi
