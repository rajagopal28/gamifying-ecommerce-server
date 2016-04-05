#!/bin/bash
source $OPENSHIFT_CARTRIDGE_SDK_BASH

echo "spring:"
echo "  datasource:"
echo "    url: jdbc:mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/$OPENSHIFT_APP_NAME"
echo "    username: $OPENSHIFT_MYSQL_DB_USERNAME"
echo "    password: $OPENSHIFT_MYSQL_DB_PASSWORD"
echo "    driveClassName: com.mysql.jdbc.Driver"
echo "                                           "
echo "  jpa:"
echo "    database: MYSQL"
echo "    show-sql: true"
echo "    hibernate:"
echo "      ddl-auto: update"
echo "      properties:"
echo "        hibernate:"
echo "          globally_quoted_identifiers: true"
