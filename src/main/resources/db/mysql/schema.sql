CREATE DATABASE IF NOT EXISTS petclinic;

ALTER DATABASE petclinic
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON petclinic.* TO pc@localhost IDENTIFIED BY 'pc';

USE petclinic;

CREATE TABLE IF NOT EXISTS vet (
  id INT(4) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  INDEX(last_name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS specialty (
  id INT(4) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(80),
  INDEX(name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS vet_specialty (
  vet INT(4)  NOT NULL,
  specialty INT(4)  NOT NULL,
  FOREIGN KEY (vet) REFERENCES vet(id),
  FOREIGN KEY (specialty) REFERENCES specialty(id),
  UNIQUE (vet,specialty)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS pet_type (
  id INT(4) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(80),
  INDEX(name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS owner (
  id INT(4) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  address VARCHAR(255),
  city VARCHAR(80),
  telephone VARCHAR(20),
  INDEX(last_name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS pet (
  id INT(4) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30),
  birth_date DATE,
  type_id INT(4) NOT NULL,
  owner_id INT(4) NOT NULL,
  INDEX(name),
  FOREIGN KEY (owner_id) REFERENCES owner(id),
  FOREIGN KEY (type_id) REFERENCES pet_type(id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS visit (
  id INT(4) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  pet_id INT(4) NOT NULL,
  date DATE,
  description VARCHAR(255),
  FOREIGN KEY (pet_id) REFERENCES pet(id)
) engine=InnoDB;
