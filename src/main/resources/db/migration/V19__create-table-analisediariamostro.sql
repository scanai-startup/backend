CREATE TABLE IF NOT EXISTS tb_analise_diaria_mostro (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fkmostro` INT NOT NULL,
  `fkfuncionario` INT NOT NULL,
  `densidade` FLOAT NOT NULL,
  `data` DATE NOT NULL,
  `temperatura` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_idFuncionario_idx` (`fkfuncionario` ASC) VISIBLE,
  INDEX `fk_idMostroAnaliseMostro_idx` (`fkmostro` ASC) VISIBLE,
  CONSTRAINT `fk_idFuncionarioAnaliseMostro`
    FOREIGN KEY (`fkfuncionario`)
    REFERENCES `tb_funcionario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idMostroAnaliseMostro`
    FOREIGN KEY (`fkmostro`)
    REFERENCES `tb_mostro` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
