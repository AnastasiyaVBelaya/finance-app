\c user_service

CREATE SCHEMA users_schema AUTHORIZATION user_app_user;

CREATE TABLE users_schema.users
(
    uuid      UUID PRIMARY KEY,
    dt_create TIMESTAMP(3) NOT NULL,
    dt_update TIMESTAMP(3) NOT NULL ,
    mail      VARCHAR(255)   NOT NULL,
    fio       VARCHAR(255)   NOT NULL,
    role      VARCHAR(50)   NOT NULL,
    status    VARCHAR(50)  NOT NULL,
    password  VARCHAR(255)  NOT NULL,
    CONSTRAINT currency_unique_title UNIQUE (mail),
    CONSTRAINT users_dt_update_check CHECK (dt_update >= dt_create)
);


ALTER TABLE IF EXISTS users_schema.users OWNER TO user_app_user;

CREATE TABLE users_schema.verification_codes (
    verification_code UUID PRIMARY KEY,
    code VARCHAR(36) NOT NULL,
    expiry_time TIMESTAMP(3) NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users_schema.users(uuid)
);

-- Предоставление прав на схему и таблицы
GRANT USAGE, CREATE ON SCHEMA users_schema TO user_app_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA users_schema TO user_app_user;
