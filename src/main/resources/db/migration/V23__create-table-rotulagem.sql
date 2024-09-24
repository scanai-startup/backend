CREATE TABLE IF NOT EXISTS tb_rotulagem (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fkenchimento` INT NOT NULL,
  `fkresprotulagem` INT NOT NULL,
  `fkrespembalamento` INT NOT NULL,
  `fkrespproducao` INT NOT NULL,
  `datainiciorotulagem` DATETIME NOT NULL,
  `datafimrotulagem` DATETIME NOT NULL,
  `funcionamentolavadorarotulagem` TINYINT NOT NULL,
  `funcionamentosecadorarotulagem` TINYINT NOT NULL,
  `equipamentosokrotulagem` TINYINT NOT NULL,
  `capsulaacordocapsuladora` TINYINT NOT NULL,
  `capsulagem` TINYINT NOT NULL,
  `defeitoscapsuladora` TINYINT NOT NULL,
  `materiaisrotuladora` TINYINT NOT NULL,
  `defeitosvisuaisrotuladora` TINYINT NOT NULL,
  `descricaorotuladora` TINYINT NOT NULL,
  `marcacaorotuladora` TINYINT NOT NULL,
  `imagemrotuladora` TINYINT NOT NULL,
  `caixaacordoembaladora` TINYINT NOT NULL,
  `separadoresembaladora` TINYINT NOT NULL,
  `colocacaoseparadoresembaladora` TINYINT NOT NULL,
  `selagemembaladora` TINYINT NOT NULL,
  `marcacaoembaladora` TINYINT NOT NULL,
  `humidadepaletizadora` TINYINT NOT NULL,
  `dossierpaletizadora` TINYINT NOT NULL,
  `qttcaixaspaletizadora` INT NOT NULL,
  `identificacaopaletizadora` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tb_rotolugem_1_idx` (`fkenchimento` ASC) VISIBLE,
  INDEX `fk_tb_rotolugem_2_idx` (`fkresprotulagem` ASC) VISIBLE,
  INDEX `fk_tb_rotolugem_3_idx` (`fkrespembalamento` ASC) VISIBLE,
  INDEX `fk_tb_rotolugem_4_idx` (`fkrespproducao` ASC) VISIBLE,
  CONSTRAINT `fk_tb_rotolugem_1`
    FOREIGN KEY (`fkenchimento`)
    REFERENCES tb_enchimento (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_rotolugem_2`
    FOREIGN KEY (`fkresprotulagem`)
    REFERENCES tb_funcionario (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_rotolugem_3`
    FOREIGN KEY (`fkrespembalamento`)
    REFERENCES tb_funcionario (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_rotolugem_4`
    FOREIGN KEY (`fkrespproducao`)
    REFERENCES tb_funcionario (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB