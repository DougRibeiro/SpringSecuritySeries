# SpringSecuritySeries


mysql docker commands:
docker run --name spring-mysql -e MYSQL_ROOT_PASSWORD=rootpass -p 3306:3306 mysql

Install mysql cli
brew install mysql-client

add mysqlcli to the bash_profile:
echo 'export PATH=/usr/local/mysql/bin:$PATH' >> ~/.bash_profile

login to the msql instance:
mysql -u root -prootpass -h 127.0.0.1

create a database for the demo.
CREATE DATABASE ssdemo;
commit;
