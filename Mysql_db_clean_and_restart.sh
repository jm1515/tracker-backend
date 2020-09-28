#!/usr/bin/env bash
# This script will stop and delete the running mysql-ppd container and restart it.
# Usage : ./Mysql_db_clean_and_restart.sh
# Then you can access it with docker exec -it mysql-ppd mysql -uroot -p
# password is "password"
# show databases;
# use ppd_gps;
# create user 'root'@'%' identified by 'password';
# grant all on ppd_gps.* to 'root'@'%';
# show tables;

set -ex

if [[ "$(docker ps -q -f name=mysql-ppd)" ]]; then
    docker stop mysql-ppd
fi

if [[ "$(docker ps -aq -f status=exited -f name=mysql-ppd)" ]]; then
    docker rm mysql-ppd
fi

docker run -d -p 6033:3306 --name=mysql-ppd --env="MYSQL_ROOT_PASSWORD=password" --env="MYSQL_PASSWORD=root" --env="MYSQL_DATABASE=ppd_gps" mysql/mysql-server:8.0

exit 0