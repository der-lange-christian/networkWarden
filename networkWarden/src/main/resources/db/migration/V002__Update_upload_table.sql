
-- ----------------------------------------------
-- DDL Statements for tables upload
-- ----------------------------------------------

-- DTYPE is to distingish derived upload-classes
ALTER TABLE APP.UPLOAD
ADD "DTYPE" VARCHAR(31);

-- for music-class
ALTER TABLE APP.UPLOAD
ADD "ALBUMNAME" VARCHAR(255);

-- for music-class
ALTER TABLE APP.UPLOAD
ADD "ARTIST" VARCHAR(255);
