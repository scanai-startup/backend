CREATE TABLE IF NOT EXISTS tb_mostro (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fimfermentacao` DATE NULL,
  `fkfuncionario` INT NOT NULL,
  `volume` FLOAT NOT NULL,
  `valid` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_TB_MOSTRO_1_idx` (`fkfuncionario` ASC) VISIBLE,
  CONSTRAINT `fk_TB_MOSTRO_1`
    FOREIGN KEY (`fkfuncionario`)
    REFERENCES `tb_funcionario` (`id`)
    )
