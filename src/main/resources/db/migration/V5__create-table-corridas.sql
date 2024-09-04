CREATE TABLE corridas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    campeonato_id INT,
    kartodromo_id INT,
    nome VARCHAR(45) NOT NULL,
    data DATE NOT NULL,
    horario TIME NOT NULL,
    transmissao BOOLEAN DEFAULT 0,
    categoria VARCHAR(20) DEFAULT 'LIVRE',
    classificacao VARCHAR(20) NOT NULL,
    codigo VARCHAR(45) UNIQUE,
    preco DECIMAL(10,2) CHECK (preco >= 0) NOT NULL,
    ativo TINYINT(1) DEFAULT 1,
    FOREIGN KEY (campeonato_id) REFERENCES campeonatos(id),
    FOREIGN KEY (kartodromo_id) REFERENCES kartodromos(id)
);
