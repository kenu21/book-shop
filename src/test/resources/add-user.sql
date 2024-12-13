INSERT INTO users (id, email, password, first_name, last_name, shipping_address, is_deleted)
VALUES (2, 'test2@example.com', "$2a$10$KmppU3ONvsZbOYfdfLvUU.bHPAgAZk6G7U8m9.O2RG5ywR8nMAbY6",
 'FirstTest2', 'LastTest2', 'Kyiv', 0);

INSERT INTO users_roles (user_id, role_id)
VALUES (2, 1);

INSERT INTO shopping_carts (user_id, is_deleted)
VALUES (2, 0);
