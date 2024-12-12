INSERT INTO orders (id, user_id, status, total, order_date, shipping_address, is_deleted)
VALUES (1, 2, 'PENDING', 100, NOW(), 'Kyiv', 0);

INSERT INTO order_items (id, order_id, book_id, quantity, price, is_deleted)
VALUES (1, 1, 1, 4, 25, 0);
