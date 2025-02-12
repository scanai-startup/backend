CREATE TABLE IF NOT EXISTS tb_mostrovinho (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fkmostro` INT NOT NULL,
  `fkvinho` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tb_mostrovinho_1_idx` (`fkmostro` ASC) VISIBLE,
  INDEX `fk_tb_mostrovinho_2_idx` (`fkvinho` ASC) VISIBLE,
  CONSTRAINT `fk_tb_mostrovinho_1`
    FOREIGN KEY (`fkmostro`)
    REFERENCES `tb_mostro` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_mostrovinho_2`
    FOREIGN KEY (`fkvinho`)
    REFERENCES `tb_vinho` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
