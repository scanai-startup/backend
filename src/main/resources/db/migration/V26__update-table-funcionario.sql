ALTER TABLE tb_funcionario
ADD CONSTRAINT email_unique UNIQUE (email),
ADD CONSTRAINT matricula_unique UNIQUE (matricula);
