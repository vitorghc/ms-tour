CREATE TABLE TBL_PROMOCAO (id INTEGER AUTO_INCREMENT PRIMARY KEY,
                             descricao VARCHAR(255) NOT NULL,
                             local VARCHAR(50) NOT NULL,
                             is_all_inclusive TINYINT(1) NOT NULL,
                             qtd_dias INTEGER,
                             preco DECIMAL(10,2),
                             );