CREATE TABLE IF NOT EXISTS `tb_rotulo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `valid` TINYINT NOT NULL,
  PRIMARY KEY (`id`))