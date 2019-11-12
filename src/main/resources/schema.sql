CREATE TABLE book(
  book_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  book_name VARCHAR(40) NOT NULL UNIQUE,
  book_count INT NOT NULL ,
  author_name VARCHAR(40) NOT NULL,
  category VARCHAR(20) NOT NULL,
  desciption VARCHAR(255),
  added_date DATETIME
);


