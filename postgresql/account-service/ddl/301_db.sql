CREATE USER account_app_user PASSWORD '123';

CREATE DATABASE account_service
    OWNER account_app_user;
