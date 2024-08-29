CREATE TABLE kartodromos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    rua VARCHAR(45) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    bairro VARCHAR(45) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    cidade VARCHAR(45),
    estado VARCHAR(45),
    endereco_foto VARCHAR(100),
    ativo TINYINT(1) DEFAULT 1
);