CREATE TABLE inscricoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    corrida_id INT NOT NULL,
    usuario_id INT NOT NULL,
    status_pgto ENUM('pago', 'pendente', 'cancelado') DEFAULT 'pendente',
    ativo TINYINT(1) DEFAULT 1,
    FOREIGN KEY (corrida_id) REFERENCES corridas(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

