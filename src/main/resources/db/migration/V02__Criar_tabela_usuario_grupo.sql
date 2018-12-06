CREATE TABLE auth.user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(60),
    login VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    user_type INT NOT NULL,
    activated BOOL DEFAULT true,
    deleted BOOL DEFAULT false,
    user_register BIGINT(5),
    date_register timestamp NULL,
    user_last_update BIGINT(5),
    date_last_update timestamp NULL,
    zone_register VARCHAR(50),
    zone_last_update VARCHAR(50),
    ip_register VARCHAR(50),
    ip_last_update VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE auth.group_user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    description VARCHAR(100),
    deleted BOOL DEFAULT false,
    user_register BIGINT(5),
    date_register timestamp NULL,
    user_last_update BIGINT(5),
    date_last_update timestamp NULL,
    zone_register VARCHAR(50),
    zone_last_update VARCHAR(50),
    ip_register VARCHAR(50) NULL,
    ip_last_update VARCHAR(50) NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE auth.user_group (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT(20) NOT NULL,
    group_id BIGINT(20) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES auth.user(id),
    FOREIGN KEY (group_id) REFERENCES auth.group_user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;