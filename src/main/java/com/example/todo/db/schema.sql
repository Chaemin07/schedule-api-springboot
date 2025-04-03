DROP DATABASE IF EXISTS user;
DROP DATABASE IF EXISTS schedule;
DROP DATABASE IF EXISTS board;


DROP TABLE IF EXISTS Schedule;
DROP TABLE IF EXISTS User;

USE todo_db;
-- user 테이블에 생성일, 수정일 컬럼 안 만들어서 에러 발생햇음
CREATE TABLE User
(
    user_id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name     VARCHAR(50)  NOT NULL,
    user_email    VARCHAR(255) NOT NULL UNIQUE,
    user_password VARCHAR(30)  NOT NULL,
    created_at    DATETIME,
    updated_at    DATETIME
);

CREATE TABLE Schedule
(
    schedule_id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id           BIGINT       NOT NULL,
    user_password     VARCHAR(30)  NOT NULL,
    schedule_title    VARCHAR(100) NOT NULL,
    schedule_contents TEXT,
    created_at        DATETIME,
    updated_at        DATETIME,
    start_time        DATETIME,
    end_time          DATETIME,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES User (user_id) ON DELETE CASCADE
);

/*
ALTER TABLE user
    ADD COLUMN created_at DATETIME,
    ADD COLUMN updated_at DATETIME;

 */