CREATE TABLE IF NOT EXISTS `mydb`.`TB_FUNCIONARIO` (
  `pk_id_funcionario` INT NOT NULL AUTO_INCREMENT,
  `matricula_funcionario` VARCHAR(45) NOT NULL,
  `email_funcionario` VARCHAR(45) NOT NULL,
  `nome_funcionario` VARCHAR(45) NOT NULL,
  `cpf_funcionario` VARCHAR(11) NOT NULL,
  PRIMARY KEY (`pk_id_funcionario`),
  UNIQUE INDEX `cpf_funcionario_UNIQUE` (`cpf_funcionario` ASC) VISIBLE)
ENGINE = InnoDB