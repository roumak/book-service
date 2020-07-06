CREATE TABLE book
(
    book_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    book_isbn VARCHAR(13) NOT NULL UNIQUE,
    book_title VARCHAR(40),
    author_name VARCHAR(50),
    book_type VARCHAR(20),
    book_mrp DECIMAL(10,2),
    book_cp DECIMAL(10,2) NOT NULL,
    book_discount DECIMAL (4,2),
    book_category VARCHAR(50),
    book_description  VARCHAR(255),
    book_count INT NOT NULL,
    added_date  DATETIME
);


