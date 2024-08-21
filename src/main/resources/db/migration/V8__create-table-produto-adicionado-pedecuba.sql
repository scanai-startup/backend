CREATE TABLE IF NOT EXISTS tb_produto_adicionado_pedecuba (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fkpedecuba` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `quantidade` FLOAT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_idPeDeCuba_idx` (`fkpedecuba` ASC) VISIBLE,
  CONSTRAINT `fk_idPeDeCubaProdutoAdicionado`
    FOREIGN KEY (`fkpedecuba`)
    REFERENCES `scanAi`.`tb_pe_de_cuba` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)