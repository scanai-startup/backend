CREATE TABLE IF NOT EXISTS tb_pe_de_cuba (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fkfuncionario` INT NOT NULL,
  `datafimfermentacao` DATE NULL,
  `datainicio` DATE NULL,
  `volume` FLOAT NOT NULL,
  `valid` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_idUsuario_idx` (`fkfuncionario` ASC) VISIBLE,
  CONSTRAINT `fk_idUsuarioPeDeCuba`
    FOREIGN KEY (`fkfuncionario`)
    REFERENCES `tb_funcionario` (`id`)
    )
