CREATE TABLE IF NOT EXISTS tb_deposito (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipodeposito` VARCHAR(50) NOT NULL,
  `numerodeposito` VARCHAR(50) NOT NULL,
  `valid` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `numerodeposito_UNIQUE` (`numerodeposito` ASC) VISIBLE
);