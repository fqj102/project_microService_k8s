#!/usr/bin/env bash
mvn package

docker build -t user-edge-service:latest .

#docker run -idt -p 8082:8082 imooc-user-edge-serivce:latest --mysql.address=192.168.1.7