\c classifier_service

CREATE SCHEMA classifier AUTHORIZATION classifier_app_user;

CREATE TABLE classifier.currency
(
    uuid UUID PRIMARY KEY,
    dt_create TIMESTAMP (3) NOT NULL,
    dt_update TIMESTAMP (3),
    title VARCHAR NOT NULL,
    description VARCHAR,
    CONSTRAINT currency_unique_title UNIQUE (title)
);


ALTER TABLE IF EXISTS classifier.currency OWNER TO classifier_app_user;


CREATE TABLE classifier.operation_category
(
    uuid UUID PRIMARY KEY,
    dt_create TIMESTAMP (3) NOT NULL,
    dt_update TIMESTAMP (3),
    title VARCHAR NOT NULL,
    CONSTRAINT operation_category_unique_title UNIQUE (title)
);


ALTER TABLE IF EXISTS classifier.operation_category OWNER TO classifier_app_user;

-- Предоставление прав на схему и таблицы
GRANT USAGE, CREATE ON SCHEMA classifier TO classifier_app_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA classifier TO classifier_app_user;
