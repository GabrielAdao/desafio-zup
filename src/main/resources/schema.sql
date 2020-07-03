CREATE TABLE tabUser
(
 id SERIAL,
 nome varchar(100) NOT NULL,
 idade integer NOT NULL,
 CPF varchar(100) NOT NULL,
 email varchar(100) DEFAULT NULL,
 telefone integer NULL,
 endereco varchar(100) DEFAULT NULL,
 PRIMARY KEY (id)
);