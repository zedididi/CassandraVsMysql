#! /bin/bash
echo "此脚本用于搭建测试环境"
#echo "注意：运行此脚本前你要先确定拉下镜像zedididi/cassandra:v1与zedididi/mysql:v1"
#echo "docker pull zedididi/cassandra:v1"
#echo "docker pull zedididi/mysql:v1"
echo "----------------------------------------------------------------------------------------------"
echo "安装openjava-8"
sudo apt-get install openjdk-8-jdk

sudo mkdir -p datadir_cassandra datadir_mysql
sudo chmod 777 datadir_cassandra datadir_mysql
sudo chmod 777 output.log
echo "创建自定义网络   172.19.0.0/16"
sudo docker network create --subnet 172.19.0.0/16 cloudtest
echo ""
echo "搭建Cassandra集群"
echo "node1 172.17.0.2"
echo "node2 172.17.0.3"
echo "node3 172.17.0.4"

path=`pwd`
sudo docker run --name node1-cloud -d --net cloudtest --ip 172.19.0.2 -e CASSANDRA_BROADCAST_ADDRESS=172.19.0.2 -p 7000:7000 -p 9042:9042 -v $path/datadir_cassandra:/var/lib/cassandra  zedididi/cassandra:v1
sudo docker run --name node2-cloud -d --net cloudtest --ip 172.19.0.3 -e CASSANDRA_BROADCAST_ADDRESS=172.19.0.3 -p 7001:7000 -e CASSANDRA_SEEDS=172.19.0.2 zedididi/cassandra:v1
sudo docker run --name node3-cloud -d --net cloudtest --ip 172.19.0.4 -e CASSANDRA_BROADCAST_ADDRESS=172.19.0.4 -p 7002:7000 -e CASSANDRA_SEEDS=172.19.0.2 zedididi/cassandra:v1

echo "运行mysql"
sudo docker run --name mysql-cloud -d --net cloudtest --ip 172.19.0.5 -e MYSQL_ROOT_PASSWORD=123456 -p 3306:3306 -v $path/datadir_mysql:/var/lib/mysql zedididi/mysql:v1

echo "运行web组件"
sudo nohup java -jar mysql-cassandra-springboot-test-0.0.1-SNAPSHOT.jar > output.log 2>&1&
echo "-----------------------------------------------------------------------------------------------------------------------------------------"
echo "测试环境搭建完成"

