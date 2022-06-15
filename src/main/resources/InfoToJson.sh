#/bin/bash
UPTIME=`uptime`
DISKFREE=`df | grep data`
JSON_STRING=$( jq -n --arg ut "$UPTIME" --arg df "$DISKFREE" '{uptime: $ut, diskfree: $df}')
echo $JSON_STRING > ${1}/serverinfo.json
# common name json file generated from all servers and written into a folder which path is:

# SERVERNAME=$(uname -n)
# curl -X PUT -d ${1}/serverinfo.json http://localhost:8080/sauron/server/$SERVERNAME