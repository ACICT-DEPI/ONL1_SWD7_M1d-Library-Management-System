CREATE DATABASE IF NOT EXISTS defaultdb;
USE defaultdb;

-- Table: Library
CREATE TABLE Library (
                         branch_id INT AUTO_INCREMENT PRIMARY KEY,
                         branch_name VARCHAR(255) NOT NULL,
                         branch_address VARCHAR(255)
);

-- Table: content_type
CREATE TABLE content_type (
                              type_id INT AUTO_INCREMENT PRIMARY KEY,
                              type VARCHAR(255) NOT NULL
);

-- Table: Contents
CREATE TABLE Contents (
                          content_id INT AUTO_INCREMENT PRIMARY KEY,
                          content_type INT NOT NULL,
                          lib_id INT NOT NULL,
                          title VARCHAR(255) NOT NULL,
                          author VARCHAR(255),
                          publisher VARCHAR(255),
                          production_year YEAR,
                          status ENUM('available', 'unavailable') DEFAULT 'available',
                          copies INT DEFAULT 1,
                          average_rate DECIMAL(3, 2),
                          FOREIGN KEY (content_type) REFERENCES content_type(type_id),
                          FOREIGN KEY (lib_id) REFERENCES Library(branch_id)
);

-- Table: books
CREATE TABLE books (
                       content_id INT PRIMARY KEY,
                       sno VARCHAR(50),
                       FOREIGN KEY (content_id) REFERENCES Contents(content_id)
);

-- Table: digital_media
CREATE TABLE digital_media (
                               content_id INT PRIMARY KEY,
                               media_type VARCHAR(255),
                               format VARCHAR(50),
                               platform VARCHAR(255),
                               FOREIGN KEY (content_id) REFERENCES Contents(content_id)
);

-- Table: articles
CREATE TABLE articles (
                          content_id INT PRIMARY KEY,
                          journal VARCHAR(255),
                          publication_date DATE,
                          FOREIGN KEY (content_id) REFERENCES Contents(content_id)
);

-- Table: Subscribers
CREATE TABLE Subscribers (
                             subscriber_id INT AUTO_INCREMENT PRIMARY KEY,
                             full_name VARCHAR(255),
                             email VARCHAR(255) UNIQUE NOT NULL,
                             password VARCHAR(255) NOT NULL,
                             is_admin BOOLEAN DEFAULT FALSE,
                             type VARCHAR(255),
                             address VARCHAR(255),
                             phone VARCHAR(15)
);

-- Table: content_rate
CREATE TABLE content_rate (
                              subscriber_id INT NOT NULL,
                              content_id INT NOT NULL,
                              rate DECIMAL(2, 1),
                              PRIMARY KEY (subscriber_id, content_id),
                              FOREIGN KEY (subscriber_id) REFERENCES Subscribers(subscriber_id),
                              FOREIGN KEY (content_id) REFERENCES Contents(content_id)
);

-- Table: borrowing_records
CREATE TABLE borrowing_records (
                                   subscriber_id INT NOT NULL,
                                   content_id INT NOT NULL,
                                   borrow_date DATE NOT NULL,
                                   due_date DATE,
                                   is_returned BOOLEAN DEFAULT FALSE,
                                   PRIMARY KEY (subscriber_id, content_id, borrow_date),
                                   FOREIGN KEY (subscriber_id) REFERENCES Subscribers(subscriber_id),
                                   FOREIGN KEY (content_id) REFERENCES Contents(content_id)
);

-- Table: to_read_list
CREATE TABLE to_read_list (
                              subscriber_id INT NOT NULL,
                              content_id INT NOT NULL,
                              PRIMARY KEY (subscriber_id, content_id),
                              FOREIGN KEY (subscriber_id) REFERENCES Subscribers(subscriber_id),
                              FOREIGN KEY (content_id) REFERENCES Contents(content_id)
);

-- Table: read
CREATE TABLE read (
                      subscriber_id INT NOT NULL,
                      content_id INT NOT NULL,
                      PRIMARY KEY (subscriber_id, content_id),
                      FOREIGN KEY (subscriber_id) REFERENCES Subscribers(subscriber_id),
                      FOREIGN KEY (content_id) REFERENCES Contents(content_id)
);

-- Table: waiting_list
CREATE TABLE waiting_list (
                              subscriber_id INT NOT NULL,
                              content_id INT NOT NULL,
                              date DATE NOT NULL,
                              PRIMARY KEY (subscriber_id, content_id),
                              FOREIGN KEY (subscriber_id) REFERENCES Subscribers(subscriber_id),
                              FOREIGN KEY (content_id) REFERENCES Contents(content_id)
);

-- Table: subscriptions
CREATE TABLE subscriptions (
                               subscription_id INT AUTO_INCREMENT PRIMARY KEY,
                               subscriber_id INT NOT NULL,
                               start_date DATE NOT NULL,
                               FOREIGN KEY (subscriber_id) REFERENCES Subscribers(subscriber_id)
);

-- Table: subscribers_articles (many-to-many relationship between subscribers and articles)
CREATE TABLE subscribers_articles (
                                      article_id INT NOT NULL,
                                      subscriber_id INT NOT NULL,
                                      PRIMARY KEY (article_id, subscriber_id),
                                      FOREIGN KEY (article_id) REFERENCES articles(content_id),
                                      FOREIGN KEY (subscriber_id) REFERENCES Subscribers(subscriber_id)
);
