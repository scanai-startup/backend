CREATE TABLE IF NOT EXISTS tb_higienedeposito (
  `id` INT NOT NULL AUTO_INCREMENT,
  `datahigiene` DATE NOT NULL,
  `fkdeposito` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_depositoHigiene_idx` (`fkdeposito` ASC) VISIBLE,
  CONSTRAINT `fk_depositoHigiene`
    FOREIGN KEY (`fkdeposito`)
    REFERENCES `tb_deposito` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
