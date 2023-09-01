DROP TABLE IF EXISTS book;
CREATE TABLE book (
                      id INT AUTO_INCREMENT  PRIMARY KEY,
                      title VARCHAR(50) NOT NULL,
                      author VARCHAR(50) NOT NULL,
                      category VARCHAR(50) NOT NULL,
                      stock TINYINT(1) NOT NULL
);