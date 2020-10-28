#!/usr/bin/env bash

mvn clean package -DskipTests

scp biz-data-center-web/target/biz-data-center-web-1.4.1-feature20200427-SNAPSHOT.jar liebao@172.17.162.180:~/

ssh root@172.17.162.180