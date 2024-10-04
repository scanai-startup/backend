CREATE TABLE IF NOT EXISTS tb_liberacao (
   `id` INT NOT NULL AUTO_INCREMENT,
   `qttproduzida` INT NOT NULL,
   `fkfuncionario` INT NOT NULL,
   `datainicio` DATETIME NOT NULL,
   `datafim` DATETIME NOT NULL,
   `gfs` INT NOT NULL,
   `fkrotulagem` INT NOT NULL,
   PRIMARY KEY (`id`),
    INDEX `fk_tb_liberacao_1_idx` (`fkfuncionario` ASC) VISIBLE,
    INDEX `fk_tb_liberacao_2_idx` (`fkrotulagem` ASC) VISIBLE,
    CONSTRAINT `fk_tb_liberacao_1`
    FOREIGN KEY (`fkfuncionario`)
    REFERENCES tb_funcionario (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_tb_liberacao_2`
    FOREIGN KEY (`fkrotulagem`)
    REFERENCES tb_rotulagem (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
