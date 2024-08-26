CREATE TABLE IF NOT EXISTS tb_analise_pre_fermentacao (
  `id` INT NOT NULL,
  `fkvinho` INT NOT NULL,
  `atotal` FLOAT NOT NULL,
  `acucarRed` FLOAT NOT NULL,
  `ph` INT NOT NULL,
  `so2l` FLOAT NOT NULL,
  `ta` FLOAT NOT NULL,
  `fkfuncionario` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_TB_ANALISE_PRE_FERMENTACAO_1_idx` (`fkvinho` ASC) VISIBLE,
  INDEX `fk_TB_ANALISE_PRE_FERMENTACAO_2_idx` (`fkfuncionario` ASC) VISIBLE,
  CONSTRAINT `fk_TB_ANALISE_PRE_FERMENTACAO_1`
    FOREIGN KEY (`fkvinho`)
    REFERENCES `scanAi`.`tb_vinho` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_ANALISE_PRE_FERMENTACAO_2`
    FOREIGN KEY (`fkfuncionario`)
    REFERENCES `scanAi`.`tb_funcionario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)