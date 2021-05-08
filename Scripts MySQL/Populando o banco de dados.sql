use mydb;

DELETE FROM order_item WHERE client_order_order_id > 0;

DELETE FROM client_order WHERE order_id > 0;
ALTER TABLE client_order AUTO_INCREMENT = 1;

DELETE FROM user WHERE user_id > 0;
ALTER TABLE user AUTO_INCREMENT = 1;

DELETE FROM login WHERE login_id > 0;
ALTER TABLE login AUTO_INCREMENT = 1;

DELETE FROM product WHERE product_id > 0;
ALTER TABLE product AUTO_INCREMENT = 1;

INSERT INTO login(login_username, login_password, login_token)
values('Mateuskwz', 'c8751b29c9bd8ef0b7fe9be5def40a39', '2d3855ed596b1a553922e4e5a14abda0');

INSERT INTO user(user_name, user_email, user_type, user_phone, login_login_id)
values('Mateus Kawazoe', 'matkawazoe@gmail.com', 'Administrador', 991095986, last_insert_id());

INSERT INTO login(login_username, login_password, login_token)
values('LucasShz', 'c8751b29c9bd8ef0b7fe9be5def40a39', '');

INSERT INTO user(user_name, user_email, user_type, user_phone, login_login_id)
values('Lucas Shizuno', 'luquinhas@gmail.com', 'Gerente', 101010, last_insert_id());

INSERT INTO login(login_username, login_password, login_token)
values('Luxzinho', 'c8751b29c9bd8ef0b7fe9be5def40a39', '123');

INSERT INTO user(user_name, user_email, user_type, user_phone, login_login_id)
values('Luis Felipe', 'Luizinho@gmail.com', 'Funcionário', 242424, last_insert_id());

INSERT INTO user(user_name, user_email, user_type, user_phone)
values('Lucas Camargo', 'lucas123@gmail.com', 'Cliente', 9999999);

INSERT INTO product(product_name, product_description, product_price)
values('Luva Térmica', 'Luva térmica em formato de canguru. Aguenta até 300ºC', 32.99);

INSERT INTO product(product_name, product_description, product_price)
values('Femow', 'FEMOW térmica em formato de canguru. Aguenta até 300ºC', 92.99);

INSERT INTO product(product_name, product_description, product_price)
values('PH', 'PH térmica em formato de canguru. Aguenta até 300ºC', 2.99);

INSERT INTO product(product_name, product_description, product_price)
values('MONTEIRO', 'MONTEIRO térmica em formato de canguru. Aguenta até 300ºC', 12.99);