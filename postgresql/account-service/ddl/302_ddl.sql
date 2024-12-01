\c account_service
CREATE SCHEMA account AUTHORIZATION account_app_user;
CREATE SCHEMA operation AUTHORIZATION account_app_user;


CREATE TABLE account.accounts
(
    uuid      UUID PRIMARY KEY,
    dt_create TIMESTAMP(3) NOT NULL,
    dt_update TIMESTAMP(3) NOT NULL,
    title     VARCHAR(255)   NOT NULL,
    description TEXT,
    balance   NUMERIC(15, 2) NOT NULL,
    type      VARCHAR(50)    NOT NULL,
    currency  UUID           NOT NULL,
    CONSTRAINT accounts_dt_update_check CHECK (dt_update >= dt_create)
);


CREATE TABLE operation.operations
(
    uuid          UUID PRIMARY KEY,
    dt_create     TIMESTAMP(3) NOT NULL,
    dt_update     TIMESTAMP(3) NOT NULL,
    date          TIMESTAMP(3) NOT NULL,
    description   TEXT,
    category      UUID           NOT NULL,
    value         NUMERIC(20, 2) NOT NULL,
    currency      UUID           NOT NULL,
    account_uuid  UUID           NOT NULL,
    CONSTRAINT fk_account FOREIGN KEY (account_uuid) REFERENCES account.accounts(uuid),
    CONSTRAINT operations_dt_update_check CHECK (dt_update >= dt_create)
);

-- Предоставление прав на схемы и таблицы
GRANT USAGE, CREATE ON SCHEMA account TO account_app_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA account TO account_app_user;

GRANT USAGE, CREATE ON SCHEMA operation TO account_app_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA operation TO account_app_user;
