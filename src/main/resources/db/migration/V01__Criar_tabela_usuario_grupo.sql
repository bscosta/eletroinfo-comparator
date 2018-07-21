CREATE TABLE user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(60),
    login VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    user_type INT NOT NULL,
    activated BOOL DEFAULT true,
    deleted BOOL DEFAULT false,
    user_registration BIGINT NOT NULL,
    user_last_update BIGINT NOT NULL,
    date_last_update TIMESTAMP NOT NULL,
    zone_register VARCHAR(100) NOT NULL,
    zone_last_update VARCHAR(100) NOT NULL,
    ip_register VARCHAR(50) NOT NULL,
    ip_last_update VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE group_user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    description VARCHAR(100),
    deleted BOOL DEFAULT false,
    user_registration BIGINT NOT NULL,
    user_last_update BIGINT NOT NULL,
    date_register TIMESTAMP NOT NULL,
    date_last_update TIMESTAMP NOT NULL,
    zone_register VARCHAR(100) NOT NULL,
    zone_last_update VARCHAR(100) NOT NULL,
    ip_register VARCHAR(50) NOT NULL,
    ip_last_update VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_group (
    id BIGINT NOT NULL,
    user_id BIGINT(20) NOT NULL,
    group_id BIGINT(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (group_id) REFERENCES group_user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;