
-- ----------------------------------------------
-- DDL Statements for tables
-- ----------------------------------------------

CREATE TABLE "APP"."SEQUENCE" (
    "SEQ_NAME" VARCHAR(50) NOT NULL, 
    "SEQ_COUNT" DECIMAL(15,0)
);

CREATE TABLE "APP"."UPLOAD" (
    "ID" BIGINT NOT NULL, 
    "FILENAME" VARCHAR(255), 
    "FILEPATH" VARCHAR(255), 
    "LANGUAGE" VARCHAR(255), 
    "SIZEINBYTE" BIGINT, 
    "TITLE" VARCHAR(255), 
    "UPLOADDATE" VARCHAR(255), 
    "UPLOADTIME" VARCHAR(255)
);

-- ----------------------------------------------
-- DDL Statements for keys
-- ----------------------------------------------

-- PRIMARY/UNIQUE
ALTER TABLE "APP"."SEQUENCE" ADD CONSTRAINT "SQL151107231527510" PRIMARY KEY ("SEQ_NAME");

ALTER TABLE "APP"."UPLOAD" ADD CONSTRAINT "SQL151223211011730" PRIMARY KEY ("ID");


