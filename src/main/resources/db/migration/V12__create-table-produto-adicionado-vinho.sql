CREATE TABLE IF NOT EXISTS tb_produto_adicionado_vinho (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fkvinho` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `quantidade` FLOAT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_idvinho_idx` (`fkvinho` ASC) VISIBLE,
  CONSTRAINT `fk_idvinhoProdutoAdicionado`
    FOREIGN KEY (`fkvinho`)
    REFERENCES `tb_vinho` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)