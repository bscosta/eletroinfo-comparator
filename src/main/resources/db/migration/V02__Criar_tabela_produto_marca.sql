CREATE TABLE brand (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(150),
    user_register BIGINT(5),
    date_register timestamp,
    user_last_update BIGINT(5),
    date_last_update timestamp,
    zone_register VARCHAR(50),
    zone_last_update VARCHAR(50),
    deleted BOOLEAN DEFAULT false NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE seller (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    user_register BIGINT(5),
    date_register timestamp,
    user_last_update BIGINT(5),
    date_last_update timestamp,
    zone_register VARCHAR(50),
    zone_last_update VARCHAR(50),
    deleted BOOLEAN DEFAULT false NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE provider (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    user_register BIGINT(5),
    date_register timestamp,
    user_last_update BIGINT(5),
    date_last_update timestamp,
    zone_register VARCHAR(50),
    zone_last_update VARCHAR(50),
    deleted BOOLEAN DEFAULT false NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE contact (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    type_contact VARCHAR(20) NOT NULL,
    form_contact VARCHAR(20) NOT NULL,
    name VARCHAR(130) NOT NULL,
    value_contact VARCHAR(50) NOT NULL,
    user_register BIGINT(5),
    date_register timestamp,
    user_last_update BIGINT(5),
    date_last_update timestamp,
    zone_register VARCHAR(50),
    zone_last_update VARCHAR(50),
    deleted BOOLEAN DEFAULT false NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE address (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    type_address VARCHAR(20) NOT NULL,
    type_place VARCHAR(20) NOT NULL,
    address VARCHAR(100) NOT NULL,
    number BIGINT(7) NOT NULL,
    neighborhood VARCHAR(60) NOT NULL,
    complement VARCHAR(60) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    city VARCHAR(60) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    user_register BIGINT(5),
    date_register timestamp,
    user_last_update BIGINT(5),
    date_last_update timestamp,
    zone_register VARCHAR(50),
    zone_last_update VARCHAR(50),
    deleted BOOLEAN DEFAULT false NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE product (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    brand_id BIGINT NOT NULL,
    unit_measure VARCHAR(50),
    measured_quantity BIGINT,
    user_register BIGINT(5),
    date_register timestamp,
    user_last_update BIGINT(5),
    date_last_update timestamp,
    zone_register VARCHAR(50),
    zone_last_update VARCHAR(50),
    deleted BOOLEAN DEFAULT false NOT NULL,
    FOREIGN KEY (brand_id) REFERENCES brand(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE seller_contact (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    seller_id BIGINT NOT NULL,
    contact_id BIGINT NOT NULL,
    FOREIGN KEY (seller_id) REFERENCES seller(id),
    FOREIGN KEY (contact_id) REFERENCES contact(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE provider_contact (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    provider_id BIGINT NOT NULL,
    contact_id BIGINT NOT NULL,
    FOREIGN KEY (provider_id) REFERENCES provider(id),
    FOREIGN KEY (contact_id) REFERENCES contact(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE provider_address (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    provider_id BIGINT NOT NULL,
    address_id BIGINT NOT NULL,
    user_register BIGINT(5),
    date_register timestamp,
    user_last_update BIGINT(5),
    date_last_update timestamp,
    zone_register VARCHAR(50),
    zone_last_update VARCHAR(50),
    deleted BOOLEAN DEFAULT false NOT NULL,
    FOREIGN KEY (provider_id) REFERENCES provider(id),
    FOREIGN KEY (address_id) REFERENCES address(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE product_provider_seller (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_id BIGINT NOT NULL,
    provider_id BIGINT NOT NULL,
    seller_id BIGINT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    rebate DECIMAL(10, 2) NOT NULL,
    quantity BIGINT NOT NULL,
    barcode BIGINT,
    user_register BIGINT(5),
    date_register timestamp,
    user_last_update BIGINT(5),
    date_last_update timestamp,
    zone_register VARCHAR(50),
    zone_last_update VARCHAR(50),
    deleted BOOLEAN DEFAULT false NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (provider_id) REFERENCES provider(id),
    FOREIGN KEY (seller_id) REFERENCES seller(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;