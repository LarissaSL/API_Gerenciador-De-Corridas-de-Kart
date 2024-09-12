CREATE TABLE checkins (
    id INT AUTO_INCREMENT PRIMARY KEY,
    inscricao_id INT NOT NULL,
    peso_inicial DECIMAL(5,2) CHECK (peso_inicial >= 0),
    peso_final DECIMAL(5,2) CHECK (peso_final >= 0),
    numero_do_kart INT NULL,
    lastro INT NULL,
    adv TINYINT(1) DEFAULT 1,
    data_checkin DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (inscricao_id) REFERENCES inscricoes(id)
);