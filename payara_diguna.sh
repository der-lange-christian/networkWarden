#!/bin/bash

cmd=bin/asadmin
db_name=test
db_port=1527
db_pass=APP
db_user=APP
db_pool_name=testDropAndCreate
jdbc_name=jdbc/$db_pool_name


echo " "
echo "# create and start domain"
echo "$cmd create-domain diguna"
echo "$cmd delete-domain diguna"
echo "$cmd start-domain diguna"
echo "$cmd stop-domain diguna"
echo " "
echo "# start database"
echo "$cmd start-database --dbhost localhost --dbport $db_port"
echo "$cmd stop-database --dbhost localhost --dbport $db_port"
echo " "
echo " "
echo "# create JDBC Connection Pool"
echo " "
echo "# create JDBC Connection Pool"
echo "$cmd create-jdbc-connection-pool --datasourceclassname org.apache.derby.jdbc.ClientDataSource --restype javax.sql.XADataSource --property portNumber=$db_port:password=$db_pass:user=$db_user:serverName=localhost:databaseName=$db_name:connectionAttributes=\;create\\\\=true $db_pool_name"
echo " "
echo " "
echo "# test connection pool"
echo "$cmd list-jdbc-connection-pools"
echo "$cmd ping-connection-pool $db_pool_name"

echo " "
echo "# create JDBC Resource"
echo "$cmd create-jdbc-resource --connectionpoolid $db_pool_name $jdbc_name"
echo "$cmd list-jdbc-resources"

echo " "
echo "# Deleting"
echo "$cmd delete-jdbc-resource $jdbc_name"
echo "$cmd delete-jdbc-connection-pool $db_pool_name"
echo " "

echo " "
echo " persistence.xml "
echo "   <persistence-unit name=\"$db_pool_name\" transaction-type=\"JTA\">"
echo "    <jta-data-source>$jdbc_name</jta-data-source>"
echo "    <properties>"
echo "      <property name=\"javax.persistence.schema-generation.database.action\" value=\"drop-and-create\"/>"
echo "    </properties>"
echo " "


echo " "
echo "establich connection"
echo "javadb/bin/ij"
echo "   ij> connect 'jdbc:derby://localhost:$db_port/$db_name';"
echo "   ij> select * from SYS.SYSTABLES;"
echo " "

echo " "
echo "# Deploy war"
echo "$cmd deploy test.war"
echo " "
echo "# UnDeploy Application"
echo "$cmd undeploy test"
echo " "
