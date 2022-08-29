#!/bin/bash
UPTIME=`uptime`
DISKFREE=`df -h / | grep -v Filesystem`
DATE=`date +"%Y-%m-%d-T%H:%M:%S%z"`
JSON_STRING=$( jq -n --arg ut "$UPTIME" --arg df "$DISKFREE" --arg dt "$DATE" '[{uptime: $ut, diskfree: $df, date: $dt}]')
echo $JSON_STRING > ${1}/serverinfo.json


