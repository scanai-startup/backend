CREATE TABLE IF NOT EXISTS tb_deposito_pedecuba (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fkpedecuba` INT NOT NULL,
  `fkdeposito` INT NOT NULL,
  `datainicio` DATE NOT NULL,
  `datafim` DATE NULL,
  `fkfuncionario` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_idDeposito_idx` (`fkdeposito` ASC) VISIBLE,
  INDEX `fk_idPeDeCuba_idx` (`fkpedecuba` ASC) VISIBLE,
  INDEX `fk_TB_DEPOSITO_PEDECUBA_1_idx` (`fkfuncionario` ASC) VISIBLE,
  CONSTRAINT `fk_idPeDeCubaDeposito`
    FOREIGN KEY (`fkpedecuba`)
    REFERENCES `scanAi`.`tb_pe_de_cuba` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idDepositoPedeCuba`
    FOREIGN KEY (`fkdeposito`)
    REFERENCES `scanAi`.`tb_deposito` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_DEPOSITO_PEDECUBA_1`
    FOREIGN KEY (`fkfuncionario`)
    REFERENCES `scanAi`.`tb_funcionario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
