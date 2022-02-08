#!/bin/bash
if [ -z $PROFILES ]; then
  echo "not fund please input port"
   exit 1
  else
  echo "funded"
fi
java -jar /app.jar --spring.profiles.active=$PROFILES