ALTER TABLE tb_produto_adicionado_pedecuba
ADD COLUMN unidade VARCHAR(255) DEFAULT NULL,
ADD COLUMN created_at TIMESTAMP DEFAULT NULL,
ADD COLUMN updated_at TIMESTAMP DEFAULT NULL;