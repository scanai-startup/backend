ALTER TABLE tb_mostro
ADD COLUMN fkmostro1 INT DEFAULT NULL,
ADD COLUMN fkmostro2 INT DEFAULT NULL,
ADD CONSTRAINT fk_mostro1
    FOREIGN KEY (fkmostro1)
    REFERENCES tb_mostro (id),
ADD CONSTRAINT fk_mostro2
    FOREIGN KEY (fkmostro2)
    REFERENCES tb_mostro (id);
