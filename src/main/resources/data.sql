DROP TABLE IF EXISTS counters;

CREATE TABLE counters (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  value INT
);