CREATE TABLE usuarios
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    nome               VARCHAR(45),
    sobrenome          VARCHAR(45),
    cpf                VARCHAR(11) UNIQUE NOT NULL,
    telefone           VARCHAR(11) UNIQUE NOT NULL,
    tipo               ENUM('admin', 'usuario') DEFAULT 'usuario',
    email              VARCHAR(45) UNIQUE NOT NULL,
    senha              VARCHAR(45)        NOT NULL,
    data_de_nascimento DATE,
    ativo              TINYINT(1) DEFAULT 1
);