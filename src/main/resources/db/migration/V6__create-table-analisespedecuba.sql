CREATE TABLE IF NOT EXISTS tb_analise_diaria_pedecuba (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fkpedecuba` INT NOT NULL,
  `fkfuncionario` INT NOT NULL,
  `densidade` FLOAT NOT NULL,
  `data` DATE NOT NULL,
  `temperatura` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_idFuncionario_idx` (`fkfuncionario` ASC) VISIBLE,
  INDEX `fk_idPeDeCuba_idx` (`fkpedecuba` ASC) VISIBLE,
  CONSTRAINT `fk_idFuncionarioAnalisePeDeCuba`
    FOREIGN KEY (`fkfuncionario`)
    REFERENCES `tb_funcionario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idPeDeCubaAnalisePeDeCuba`
    FOREIGN KEY (`fkpedecuba`)
    REFERENCES `tb_pe_de_cuba` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
