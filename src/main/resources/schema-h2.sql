
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS ADDRESS;

CREATE TABLE ADDRESS (

ID BIGINT AUTO_INCREMENT PRIMARY KEY,
STREET VARCHAR(100),
CITY VARCHAR(50),
STATE VARCHAR (25),
POSTCODE INT,
CREATEDDATETIME DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE USERS (

EMPID BIGINT PRIMARY KEY,
ADDRESSID BIGINT,
TITLE VARCHAR(20),
FIRSTNAME VARCHAR(50),
LASTNAME VARCHAR(50),
GENDER VARCHAR(20),
CREATEDDATETIME DATETIME DEFAULT CURRENT_TIMESTAMP,
UPDATEDDATETIME DATETIME DEFAULT CURRENT_TIMESTAMP,

CONSTRAINT FK_USERS_ADDRESS FOREIGN KEY (ADDRESSID) REFERENCES ADDRESS(ID) ON DELETE CASCADE
);