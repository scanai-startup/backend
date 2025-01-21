ALTER TABLE tb_deposito
    ADD COLUMN `capacidade` FLOAT NOT NULL;

UPDATE tb_deposito
SET capacidade = 0;