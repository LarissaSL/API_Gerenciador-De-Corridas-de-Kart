CREATE TABLE IF NOT EXISTS logins_temporarios (
  id INT NOT NULL AUTO_INCREMENT,
  usuarios_id INT NOT NULL,
  codigo_temporario VARCHAR(10) NULL,
  data_expiracao DATETIME NULL,
  utilizado TINYINT(1) NULL,
  data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  data_utilizado DATETIME NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (usuarios_id) REFERENCES usuarios (id)
);