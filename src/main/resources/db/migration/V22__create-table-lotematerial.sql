CREATE TABLE IF NOT EXISTS tb_lotematerial(
  `id` INT NOT NULL AUTO_INCREMENT,
  `fornecedor` VARCHAR(45) NOT NULL,
  `numerolote` VARCHAR(45) NOT NULL,
  `fkmaterial` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tb_lotematerial_1_idx` (`fkmaterial` ASC) VISIBLE,
  CONSTRAINT `fk_tb_lotematerial_1`
    FOREIGN KEY (`fkmaterial`)
    REFERENCES tb_material(`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)