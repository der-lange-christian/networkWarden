

- https://builds.apache.org/job/Derby-docs/lastSuccessfulBuild/artifact/trunk/out/getstart/index.html
- https://builds.apache.org/job/Derby-docs/lastSuccessfulBuild/artifact/trunk/out/getstart/twwdactivity1.html
- https://gist.github.com/mortezaadi/8619433

# Create Database
run ij in Derby-Directory

CONNECT 'jdbc:derby:firstdb;create=true';

## connect
The ij command to establish a connection to a database. The Derby connection URL is enclosed in single quotation marks. An ij command can be in either uppercase or lowercase.
## jdbc:derby:
The JDBC protocol specification for the Derby driver.
## firstdb
The name of the database. The name can be any string. Because no filepath is specified, the database is created in the default working directory (DERBYTUTOR).
## ;create=true
The Derby URL attribute that is used to create a database. Derby does not have an SQL create database command.
## ;
The semicolon is the ij command terminator.

# Create Table and data in ij

CREATE TABLE FIRSTTABLE (ID INT PRIMARY KEY, NAME VARCHAR(12));
INSERT INTO FIRSTTABLE VALUES (10,'TEN'),(20,'TWENTY'),(30,'THIRTY');
SELECT * FROM FIRSTTABLE;
exit;

# start NetworkServer
- ./startNetworkServer
- connect from Netbeans to jdbc:derby://localhost:1527/firstdb