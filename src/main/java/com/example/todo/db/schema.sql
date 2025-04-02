DROP DATABASE IF EXISTS user;
DROP DATABASE IF EXISTS schedule;
DROP DATABASE IF EXISTS board;


DROP TABLE IF EXISTS Schedule;
DROP TABLE IF EXISTS User;

USE todo_db;

CREATE TABLE User
(
    userId       BIGINT AUTO_INCREMENT PRIMARY KEY,
    userName     VARCHAR(50)  NOT NULL,
    userPassword VARCHAR(30)  NOT NULL,
    userEmail    VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE Schedule
(
    taskId        BIGINT AUTO_INCREMENT PRIMARY KEY,
    userId        BIGINT       NOT NULL,
    userPassword  VARCHAR(30)  NOT NULL,
    scheduleTitle VARCHAR(100) NOT NULL,
    contents      TEXT,
    createdAt     DATETIME,
    updatedAt     DATETIME,
    startTime     DATETIME,
    endTime       DATETIME,
    CONSTRAINT fk_user FOREIGN KEY (userId) REFERENCES User (userId) ON DELETE CASCADE
);