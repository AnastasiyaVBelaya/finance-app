\c user_service

INSERT INTO users_schema.users (uuid, mail, fio, role, status, password, dt_create, dt_update)
VALUES (gen_random_uuid(),
        'mainDestroyerHere@mail.ru',
        'Администратор',
        'ADMIN',
        'ACTIVATED',
        '$2a$10$AN4.blXLYeiNZ1wmJPPJEOXTL4dJ5FVut3JWBcnJ9/1fL7N1pZkya', --"UltraAdminPass!2024"
        now(),
        now());
