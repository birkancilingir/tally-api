DROP TABLE IF EXISTS counters;

CREATE TABLE counters (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  value INT
);

DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  surname VARCHAR(250) NOT NULL,
  username VARCHAR(250) NOT NULL UNIQUE,
  password VARCHAR(250) NOT NULL,
  passwordSalt VARCHAR(250) NOT NULL,
  emailAddress VARCHAR(250) NOT NULL
 );

INSERT INTO users (name, surname, username, password, passwordSalt, emailAddress)
    VALUES ('Lorem', 'Ipsum', 'lorem.ipsum', 'Passw0rd', '', 'lorem.ipsum@dummy.com');
INSERT INTO users (name, surname, username, password, passwordSalt, emailAddress)
    VALUES ('Admin', 'Ipsum', 'admin.ipsum', 'Passw0rd', '', 'admin.ipsum@dummy.com');

DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('USER');