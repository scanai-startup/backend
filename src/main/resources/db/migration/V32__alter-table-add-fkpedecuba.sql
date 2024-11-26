ALTER TABLE tb_pe_de_cuba
ADD COLUMN fkpedecuba INT DEFAULT NULL,
ADD CONSTRAINT fk_pedecuba_auto
    FOREIGN KEY (fkpedecuba)
    REFERENCES tb_pe_de_cuba (id);
