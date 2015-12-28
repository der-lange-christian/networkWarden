
- http://programming.manessinger.com/tutorials/an-eclipse-glassfish-java-ee-6-tutorial/#toc-creating-connection-pools-and-jdbc-resources-using-asadmin

./asadmin --port 4860 --user admin \
    create-jdbc-connection-pool \
    --datasourceclassname org.apache.derby.jdbc.ClientDataSource \
    --restype javax.sql.DataSource \
    --property portNumber=1527:password=cookbook:user=cookbookuser:serverName=localhost:databaseName=cookbookdb:connectionAttributes=;create\=true CookbookPool


# Anlegen 

./asadmin create-jdbc-connection-pool                                     \
--datasourceclassname org.apache.derby.jdbc.ClientDataSource    \
--restype javax.sql.XADataSource                                \
--property portNumber=1527:password=APP:user=APP:serverName=localhost:databaseName=sun-appserv-samples:connectionAttributes=\;create\\=true sample_derby_pool

# Ansehen
./asadmin --port 4848 --user admin list-jdbc-connection-pools

# Ping
./asadmin --port 4848 --user admin ping-connection-pool sample_derby_pool

# JDBC-Resource-Definition
./asadmin --port 4848 --user admin create-jdbc-resource --connectionpoolid sample_derby_pool jdbc/sampleDerbyPool

# Ansehen
./asadmin --port 4848 --user admin list-jdbc-resources

# persistence.xml
  <persistence-unit name="prod" transaction-type="JTA">
    <jta-data-source>jdbc/sampleDerbyPool</jta-data-source>
    <properties>
      <property name="javax.persistence.schema-generation.database.action" value="none"/>
    </properties>
  </persistence-unit>

# ij - connection to Derby-DB
./ij
connect 'jdbc:derby://localhost:1527/sun-appserv-samples';
select * from SYS.SYSTABLES;

