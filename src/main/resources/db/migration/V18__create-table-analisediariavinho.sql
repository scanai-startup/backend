CREATE TABLE IF NOT EXISTS tb_analise_diaria_vinho (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fkvinho` INT NOT NULL,
  `fkfuncionario` INT NOT NULL,
  `densidade` FLOAT NOT NULL,
  `data` DATE NOT NULL,
  `temperatura` FLOAT NOT NULL,
  `pressao` FLOAT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_idFuncionario_idx` (`fkfuncionario` ASC) VISIBLE,
  INDEX `fk_idVinhoFermentacao_idx` (`fkvinho` ASC) VISIBLE,
  CONSTRAINT `fk_idFuncionarioAnaliseVinhoFermentacao`
    FOREIGN KEY (`fkfuncionario`)
    REFERENCES `scanAi`.`tb_funcionario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idVinhoFermentacaoAnaliseVinhoFermentacao`
    FOREIGN KEY (`fkvinho`)
    REFERENCES `scanAi`.`tb_vinho` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)