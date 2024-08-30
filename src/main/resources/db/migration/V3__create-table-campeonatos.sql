CREATE TABLE campeonatos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    data_inicial DATE NOT NULL,
    data_final DATE NOT NULL,
    ativo TINYINT(1) DEFAULT 1
);

