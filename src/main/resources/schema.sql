CREATE TABLE IF NOT EXISTS password
(
    uuid      BINARY(16)   NOT NULL,
    milestone DATETIME(6)  NOT NULL,
    value     VARCHAR(255) NOT NULL,
    user_id   BINARY(16)   NOT NULL,
    PRIMARY KEY (uuid)
);

CREATE TABLE IF NOT EXISTS status
(
    id    INTEGER      NOT NULL AUTO_INCREMENT,
    value VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS status_history
(
    uuid      BINARY(16)  NOT NULL,
    milestone DATETIME(6) NOT NULL,
    status_id INTEGER     NOT NULL,
    user_id   BINARY(16)  NOT NULL,
    PRIMARY KEY (uuid)
);

CREATE TABLE IF NOT EXISTS user
(
    uuid       BINARY(16)   NOT NULL,
    email      VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    PRIMARY KEY (uuid)
);

#ALTER TABLE status
# DROP INDEX unique_value;

ALTER TABLE status
    ADD CONSTRAINT unique_value
        UNIQUE (value);

#ALTER TABLE user
 #   DROP INDEX unique_email;

ALTER TABLE user
    ADD CONSTRAINT unique_email
        UNIQUE (email);

ALTER TABLE password
    ADD CONSTRAINT FK_password_user
        FOREIGN KEY (user_id)
            REFERENCES user (uuid);

ALTER TABLE status_history
    ADD CONSTRAINT FK_status_history_status
        FOREIGN KEY (status_id)
            REFERENCES status (id);

ALTER TABLE status_history
    ADD CONSTRAINT FK_status_history_user
        FOREIGN KEY (user_id)
            REFERENCES user (uuid);