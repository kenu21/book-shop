INSERT INTO books (id, title, author, isbn, price, description, cover_image, is_deleted)
VALUES (1, 'Test Book', 'Author1', '123-4567890123', 25, 'Description1', 'cover1.jpg', 0);

INSERT INTO categories (id, name, description, is_deleted)
VALUES (1, 'Fiction', 'Fictional books', 0);

INSERT INTO books_categories (book_id, category_id)
VALUES (1, 1);
