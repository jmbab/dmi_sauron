#/bin/bash
UPTIME=`uptime`
DISKFREE=`df | grep data`
JSON_STRING=$( jq -n --arg ut "$UPTIME" --arg df "$DISKFREE" '{uptime: $ut, diskfree: $df}')
echo $JSON_STRING > /Users/jean/Documents/GitHub/dmi_sauron/src/serverinfo.json
