#/bin/bash
UPTIME=`uptime`
DISKFREE=`df -h / | grep -v Filesystem`
JSON_STRING=$( jq -n --arg ut "$UPTIME" --arg df "$DISKFREE" '[{uptime: $ut, diskfree: $df}]')
echo $JSON_STRING > ${1}/serverinfo.json


