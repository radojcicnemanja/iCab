insert into customer (id, email, last_name, name, phone_number, username, password, verification_code, is_enabled) values
    (1, 'isatestmail2021@gmail.com', 'Isakovic', 'Marko', '0652443221', 'tzone', '$2a$10$1YFbGJDKf9NIpVogxwMDee370uMSVjQy/g4Y2dLqfO82DOXl44F9i', '', true);

insert into role (id, name) VALUES (1, 'ROLE_CUSTOMER');
insert into role (id, name) VALUES (2, 'ROLE_DRIVER');
insert into role (id, name) VALUES (3, 'ROLE_ADMIN');

insert into USER_ROLE (user_id, role_id) VALUES (1, 1);