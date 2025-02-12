CREATE TABLE IF NOT EXISTS tb_deposito_mostro (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fkdeposito` INT NOT NULL,
  `fkmostro` INT NOT NULL,
  `datainicio` DATETIME NOT NULL,
  `datafim` DATETIME NULL,
  `fkfuncionario` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_idDeposito_idx` (`fkdeposito` ASC) VISIBLE,
  INDEX `fk_idMostroDepositoM_idx` (`fkmostro` ASC) VISIBLE,
  INDEX `fk_TB_MOSTRO_DEPOSITO_1_idx` (`fkfuncionario` ASC) VISIBLE,
  CONSTRAINT `fk_idMostroDepositoM`
    FOREIGN KEY (`fkmostro`)
    REFERENCES `tb_mostro` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idDeposito`
    FOREIGN KEY (`fkdeposito`)
    REFERENCES `tb_deposito` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_MOSTRO_DEPOSITO_1`
    FOREIGN KEY (`fkfuncionario`)
    REFERENCES `tb_funcionario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)