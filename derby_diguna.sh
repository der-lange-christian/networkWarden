#!/bin/bash

cmd=bin/asadmin
db_name=diguna
db_port=1527
db_pass=APP
db_user=APP
db_pool_name=diguna
jdbc_name=jdbc/diguna


# create and start domain
#$cmd create-domain diguna
#$cmd delete-domain diguna
#$cmd start-domain diguna
#$cmd stop-domain diguna

# start database
#$cmd start-database --dbhost localhost --dbport $db_port
#$cmd stop-database --dbhost localhost --dbport $db_port

# create JDBC Connection Pool
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
echo "establich connection"
echo "javadb/bin/ij"
echo "   ij> connect 'jdbc:derby://localhost:$db_port/$db_name';"
echo "   ij> select * from SYS.SYSTABLES;"
echo " "
