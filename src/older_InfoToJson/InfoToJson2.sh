#/bin/bash
PATH=${1}
UPTIME=`uptime`
DISKFREE=`df | grep data`
JSON_STRING=$( jq -n --arg ut "$UPTIME" --arg df "$DISKFREE" '{uptime: $ut, diskfree: $df}')
echo $JSON_STRING > ${PATH}/serverinfo.json