CREATE TABLE IF NOT EXISTS `tb_vinho` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fkrotulo` INT NOT NULL,
  `datafimfermentacao` DATE NULL,
  `fkmostro` INT NULL,
  `fkpedecuba` INT NULL,
  `volume` FLOAT NOT NULL,
  `valid` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_idVinho_idx` (`fkrotulo` ASC) VISIBLE,
  INDEX `fk_idMostroVinhoFermentacao_idx` (`fkmostro` ASC) VISIBLE,
  INDEX `fk_idPeDeCubaVinhoFermentacao_idx` (`fkpedecuba` ASC) VISIBLE,
  CONSTRAINT `fk_idVinho`
    FOREIGN KEY (`fkrotulo`)
    REFERENCES `tb_rotulo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idMostroVinhoFermentacao`
    FOREIGN KEY (`fkmostro`)
    REFERENCES `tb_mostro` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idPeDeCubaVinhoFermentacao`
    FOREIGN KEY (`fkpedecuba`)
    REFERENCES `tb_pe_de_cuba` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)