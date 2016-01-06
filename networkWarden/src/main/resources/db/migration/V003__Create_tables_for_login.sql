
-- ----------------------------------------------
-- DDL Statements for tables
-- ----------------------------------------------

CREATE TABLE "APP"."USERS" (
    "username" VARCHAR(255) NOT NULL, 
    "password" VARCHAR(255) DEFAULT NULL
);
-- PRIMARY/UNIQUE
ALTER TABLE "APP"."USERS" ADD CONSTRAINT "SQL151107231527511" PRIMARY KEY ("username");

CREATE TABLE "APP"."GROUPS" (
    "username" VARCHAR(255) DEFAULT NULL, 
    "groupname" VARCHAR(255) DEFAULT NULL
);

CREATE INDEX groups_users_FK1 ON groups("username" ASC);

-- Test-Values
-- password1
INSERT INTO "USERS" VALUES('bob',   '0b14d501a594442a01c6859541bcb3e8164d183d32937b851835442f69d5c94e');
-- password2
INSERT INTO "USERS" VALUES('sally', '6cf615d5bcaac778352a8f1f3360d23f02f34ec182e259897fd6ce485d7870d4');
-- password3
INSERT INTO "USERS" VALUES('tom',   '5906ac361a137e2d286465cd6588ebb5ac3f5ae955001100bc41577c3d751764');
INSERT INTO "GROUPS" VALUES('bob', 'admin');
INSERT INTO "GROUPS" VALUES('sally', 'user');
INSERT INTO "GROUPS" VALUES('tom', 'user');