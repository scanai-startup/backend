CREATE TABLE IF NOT EXISTS tb_deposito_vinho (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fkvinho` INT NOT NULL,
  `fkdeposito` INT NOT NULL,
  `datainicio` DATE NOT NULL,
  `datafim` DATE NULL,
  `fkfuncionario` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_idVinhoFermentacao_idx` (`fkvinho` ASC) VISIBLE,
  INDEX `fk_idDeposito_idx` (`fkdeposito` ASC) VISIBLE,
  INDEX `fk_TB_DEPOSITO_VINHO_FERMENTACAO_1_idx` (`fkfuncionario` ASC) VISIBLE,
  CONSTRAINT `fk_idVinhoFermentacao`
    FOREIGN KEY (`fkvinho`)
    REFERENCES `scanAi`.`tb_vinho` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idDepositoVinhoFermentacao`
    FOREIGN KEY (`fkdeposito`)
    REFERENCES `scanAi`.`tb_deposito` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_DEPOSITO_VINHO_FERMENTACAO_1`
    FOREIGN KEY (`fkfuncionario`)
    REFERENCES `scanAi`.`tb_funcionario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
