# MySQL

`docker pull mysql/mysql-server:8.0`

`docker run --name=mysql-ppd -d mysql/mysql-server:8.0`

`docker run -d -p 6033:3306 --name=mysql-ppd --env="MYSQL_ROOT_PASSWORD=password" --env="MYSQL_PASSWORD=root" --env="MYSQL_DATABASE=ppd_gps" mysql/mysql-server:8.0
`

Run these commands only the first time :

`docker logs mysql-ppd 2>&1 | grep GENERATED`

`docker exec -it mysql-ppd mysql -uroot -p`

`ALTER USER 'root'@'localhost' IDENTIFIED BY 'password';`

MySQL Basic commands :

`show databases;`

`use ppd_gps;`

`create user 'root'@'%' identified by 'password';`

`grant all on ppd_gps.* to 'root'@'%';`

`show tables;`


