CREATE TABLE IF NOT EXISTS tb_enchimento (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fkvinho` INT NOT NULL,
  `volume` FLOAT NOT NULL,
  `datainiciodespaletizacao` DATETIME NOT NULL,
  `datafimdespaletizacao` DATETIME NOT NULL,
  `conformeosdespaletizacao` TINYINT NOT NULL,
  `ausenciapoeiradespaletizacao` TINYINT NOT NULL,
  `quantidadegarrafasdespaletizacao` INT NOT NULL,
  `coracordodespaletizacao` TINYINT NOT NULL,
  `datainicioenxaguadora` DATETIME NOT NULL,
  `datafimenxaguadora` DATETIME NOT NULL,
  `funcionamentoenxaguadora` TINYINT NOT NULL,
  `pressaoentradaenxaguadora` FLOAT NOT NULL,
  `pressaosaidaenxaguadora` FLOAT NOT NULL,
  `jatopercorreenxaguadora` TINYINT NOT NULL,
  `bicosfuncionandoenxaguadora` TINYINT NOT NULL,
  `ausenciaaguaenxaguadora` TINYINT NOT NULL,
  `datainicioenchedora` DATETIME NOT NULL,
  `datafimenchedora` DATETIME NOT NULL,
  `temperaturaenchedora` FLOAT NOT NULL,
  `nivelmodeloenchedora` TINYINT NOT NULL,
  `pressaoenchedora` FLOAT NOT NULL,
  `rolhaenchedora` TINYINT NOT NULL,
  `qualidaderolhaenchedora` TINYINT NOT NULL,
  `corposestranhos` TINYINT NOT NULL,
  `fkrespproducao` INT NOT NULL,
  `fkrespdespaletizacao` INT NULL,
  `fkrespenchimento` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tb_engarrafamento_1_idx` (`fkvinho` ASC) VISIBLE,
  INDEX `fk_tb_enchimento_1_idx` (`fkrespproducao` ASC) VISIBLE,
  INDEX `fk_tb_enchimento_2_idx` (`fkrespdespaletizacao` ASC) VISIBLE,
  INDEX `fk_tb_enchimento_3_idx` (`fkrespenchimento` ASC) VISIBLE,
  CONSTRAINT `fk_tb_engarrafamento_1`
    FOREIGN KEY (`fkvinho`)
    REFERENCES `tb_vinho` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_enchimento_1`
    FOREIGN KEY (`fkrespproducao`)
    REFERENCES `tb_funcionario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_enchimento_2`
    FOREIGN KEY (`fkrespdespaletizacao`)
    REFERENCES `tb_funcionario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_enchimento_3`
    FOREIGN KEY (`fkrespenchimento`)
    REFERENCES `tb_funcionario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)