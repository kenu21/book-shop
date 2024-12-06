INSERT INTO books (title, author, isbn, price, description, cover_image, is_deleted)
VALUES ('Test Book', 'Author1', '123-4567890123', 10.99, 'Description1', 'cover1.jpg', 0);

INSERT INTO categories (name, description, is_deleted)
VALUES ('Fiction', 'Fictional books', 0);

INSERT INTO books_categories (book_id, category_id)
VALUES (1, 1);
