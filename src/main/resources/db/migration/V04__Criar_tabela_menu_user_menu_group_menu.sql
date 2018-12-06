CREATE TABLE auth.menu (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NULL,
    url VARCHAR(100),
    menu_top_id int8 NULL,
    icon varchar(100) NULL,
    tag VARCHAR(100) NOT NULL,
    internal_menu BOOL DEFAULT false,
    internationalization_code VARCHAR(100),
    user_register BIGINT(5),
    date_register timestamp NULL,
    user_last_update BIGINT(5),
    date_last_update timestamp NULL,
    zone_register VARCHAR(50),
    zone_last_update VARCHAR(50),
    ip_register VARCHAR(50),
    ip_last_update VARCHAR(50),
    deleted BOOL DEFAULT false
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE auth.feature (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    code VARCHAR(100) NOT NULL,
    user_register BIGINT(5),
    date_register timestamp NULL,
    user_last_update BIGINT(5),
    date_last_update timestamp NULL,
    zone_register VARCHAR(50),
    zone_last_update VARCHAR(50),
    ip_register VARCHAR(50),
    ip_last_update VARCHAR(50),
    deleted BOOL DEFAULT false
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE auth.menu_feature (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    menu_id BIGINT NOT NULL,
    feature_id BIGINT NOT NULL,
    FOREIGN KEY (menu_id) REFERENCES auth.menu(id),
    FOREIGN KEY (feature_id) REFERENCES auth.feature(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE auth.user_menu (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    menu_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES auth.user(id),
    FOREIGN KEY (menu_id) REFERENCES auth.menu(id),
    user_register BIGINT(5),
    date_register timestamp NULL,
    user_last_update BIGINT(5),
    date_last_update timestamp NULL,
    zone_register VARCHAR(50),
    zone_last_update VARCHAR(50),
    ip_register VARCHAR(50),
    ip_last_update VARCHAR(50),
    deleted BOOL DEFAULT false
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE auth.user_menu_feature (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_menu_id BIGINT NOT NULL,
    feature_id BIGINT NOT NULL,
    FOREIGN KEY (user_menu_id) REFERENCES auth.user_menu(id),
    FOREIGN KEY (feature_id) REFERENCES auth.feature(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE auth.group_user_menu (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    group_id BIGINT NOT NULL,
    menu_id BIGINT NOT NULL,
    FOREIGN KEY (group_id) REFERENCES auth.group_user(id),
    FOREIGN KEY (menu_id) REFERENCES auth.menu(id),
    user_register BIGINT(5),
    date_register timestamp NULL,
    user_last_update BIGINT(5),
    date_last_update timestamp NULL,
    zone_register VARCHAR(50),
    zone_last_update VARCHAR(50),
    ip_register VARCHAR(50),
    ip_last_update VARCHAR(50),
    deleted BOOL DEFAULT false
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE auth.group_user_menu_feature (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    group_user_menu_id BIGINT NOT NULL,
    feature_id BIGINT NOT NULL,
    FOREIGN KEY (group_user_menu_id) REFERENCES auth.group_user_menu(id),
    FOREIGN KEY (feature_id) REFERENCES auth.feature(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;