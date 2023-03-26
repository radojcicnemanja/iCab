insert into customer (id, email, last_name, name, phone_number, username, password, verification_code, is_enabled) values
    (1, 'isatestmail2021@gmail.com', 'Pavkov', 'Nemanja', '0652443221', 'tzone', '$2a$10$1YFbGJDKf9NIpVogxwMDee370uMSVjQy/g4Y2dLqfO82DOXl44F9i', '', true);

insert into driver (id, email, last_name, name, phone_number, username, password, car_description, is_enabled) values
    (2, 'isatestmail2021@gmail.com', 'Kon', 'Marko', '0652443221', 'cone', '$2a$10$1YFbGJDKf9NIpVogxwMDee370uMSVjQy/g4Y2dLqfO82DOXl44F9i', 'Mercedes Benz 200 E 220 d Premium', true);

insert into role (id, name) VALUES (1, 'ROLE_CUSTOMER');
insert into role (id, name) VALUES (2, 'ROLE_DRIVER');
insert into role (id, name) VALUES (3, 'ROLE_ADMIN');

insert into USER_ROLE (user_id, role_id) VALUES (1, 1);
insert into USER_ROLE (user_id, role_id) VALUES (2, 2);