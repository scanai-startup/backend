CREATE TABLE IF NOT EXISTS tb_entradamaterial(
  `identrada_material` INT NOT NULL AUTO_INCREMENT,
  `fkmaterial` INT NOT NULL,
  `qttentrada` INT NOT NULL,
  `dataentrada` DATE NOT NULL,
  `valorunidade` FLOAT NOT NULL,
  PRIMARY KEY (`identrada_material`),
  INDEX `fk_entrada_material_1_idx` (`fkmaterial` ASC) VISIBLE,
  CONSTRAINT `fk_entrada_material_1`
    FOREIGN KEY (`fkmaterial`)
    REFERENCES `scanAi`.`tb_material` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)