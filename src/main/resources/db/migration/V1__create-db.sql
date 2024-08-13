-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`TB_VITICULTOR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TB_VITICULTOR` (
  `pk_id_viticultor` INT NOT NULL AUTO_INCREMENT,
  `cpf_viticultor` VARCHAR(11) NOT NULL,
  `nome_viticultor` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`pk_id_viticultor`),
  UNIQUE INDEX `cpf_viticulturer_UNIQUE` (`cpf_viticultor` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TB_FUNCIONARIO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TB_FUNCIONARIO` (
  `idTB_FUNCIONARIO` INT NOT NULL AUTO_INCREMENT,
  `matricula_funcionario` VARCHAR(45) NOT NULL,
  `email_funcionario` VARCHAR(45) NOT NULL,
  `nome_funcionario` VARCHAR(45) NOT NULL,
  `cpf_funcionario` VARCHAR(11) NOT NULL,
  PRIMARY KEY (`idTB_FUNCIONARIO`),
  UNIQUE INDEX `cpf_funcionario_UNIQUE` (`cpf_funcionario` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TB_MOSTRO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TB_MOSTRO` (
  `idTB_MOSTRO` INT NOT NULL AUTO_INCREMENT,
  `fimFermentacao_mostro` DATE NULL,
  `fk_id_funcionario` INT NOT NULL,
  `volume_mostro` FLOAT NOT NULL,
  `isValid` TINYINT NOT NULL,
  PRIMARY KEY (`idTB_MOSTRO`),
  INDEX `fk_TB_MOSTRO_1_idx` (`fk_id_funcionario` ASC) VISIBLE,
  CONSTRAINT `fk_TB_MOSTRO_1`
    FOREIGN KEY (`fk_id_funcionario`)
    REFERENCES `mydb`.`TB_FUNCIONARIO` (`idTB_FUNCIONARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TB_REMESSA_UVA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TB_REMESSA_UVA` (
  `pk_id_remessaUva` INT NOT NULL AUTO_INCREMENT,
  `fk_id_viticultor` INT NOT NULL,
  `fk_id_funcionario` INT NOT NULL,
  `dataChegada_remessaUva` DATE NOT NULL,
  `numeroTalao_remessaUva` INT NOT NULL,
  `sanidade_remessaUva` INT NOT NULL,
  `peso_remessaUva` INT NOT NULL,
  `so2_remessaUva` VARCHAR(45) NOT NULL,
  `numeroLote_remessaUva` INT NOT NULL,
  `tipoDeVinho_remessaUva` VARCHAR(45) NOT NULL,
  `casta_remessaUva` VARCHAR(45) NOT NULL,
  `fk_id_mostro` INT NULL,
  `isValid` TINYINT NOT NULL,
  PRIMARY KEY (`pk_id_remessaUva`),
  INDEX `fk_IdViticultor_remessaUva_idx` (`fk_id_viticultor` ASC) VISIBLE,
  INDEX `fk_idFuncionario_idx` (`fk_id_funcionario` ASC) VISIBLE,
  INDEX `fk_TB_REMESSA_UVA_1_idx` (`fk_id_mostro` ASC) VISIBLE,
  CONSTRAINT `fk_IdViticultor_remessaUva`
    FOREIGN KEY (`fk_id_viticultor`)
    REFERENCES `mydb`.`TB_VITICULTOR` (`pk_id_viticultor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idFuncionarioRemessaUva`
    FOREIGN KEY (`fk_id_funcionario`)
    REFERENCES `mydb`.`TB_FUNCIONARIO` (`idTB_FUNCIONARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_REMESSA_UVA_1`
    FOREIGN KEY (`fk_id_mostro`)
    REFERENCES `mydb`.`TB_MOSTRO` (`idTB_MOSTRO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TB_DEPOSITO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TB_DEPOSITO` (
  `idTB_DEPOSITO` INT NOT NULL AUTO_INCREMENT,
  `tipo_deposito` VARCHAR(50) NOT NULL,
  `numero_deposito` VARCHAR(45) NOT NULL,
  `isValid` TINYINT NOT NULL,
  PRIMARY KEY (`idTB_DEPOSITO`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TB_DATA_HIGIENE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TB_DATA_HIGIENE` (
  `idTB_DATA_HIGIENE` INT NOT NULL AUTO_INCREMENT,
  `dataHigiene` DATE NOT NULL,
  `fk_id_deposito` INT NOT NULL,
  PRIMARY KEY (`idTB_DATA_HIGIENE`),
  INDEX `fk_depositoHigiene_idx` (`fk_id_deposito` ASC) VISIBLE,
  CONSTRAINT `fk_depositoHigiene`
    FOREIGN KEY (`fk_id_deposito`)
    REFERENCES `mydb`.`TB_DEPOSITO` (`idTB_DEPOSITO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TB_MOSTRO_DEPOSITO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TB_MOSTRO_DEPOSITO` (
  `idTB_UVA_DEPOSITO_MOSTRO` INT NOT NULL AUTO_INCREMENT,
  `fk_id_deposito` INT NOT NULL,
  `fk_id_mostro` INT NOT NULL,
  `dataInicio` DATETIME NOT NULL,
  `dataFim` DATETIME NULL,
  `fk_id_funcionario` INT NOT NULL,
  PRIMARY KEY (`idTB_UVA_DEPOSITO_MOSTRO`),
  INDEX `fk_idDeposito_idx` (`fk_id_deposito` ASC) VISIBLE,
  INDEX `fk_idMostroDepositoM_idx` (`fk_id_mostro` ASC) VISIBLE,
  INDEX `fk_TB_MOSTRO_DEPOSITO_1_idx` (`fk_id_funcionario` ASC) VISIBLE,
  CONSTRAINT `fk_idMostroDepositoM`
    FOREIGN KEY (`fk_id_mostro`)
    REFERENCES `mydb`.`TB_MOSTRO` (`idTB_MOSTRO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idDeposito`
    FOREIGN KEY (`fk_id_deposito`)
    REFERENCES `mydb`.`TB_DEPOSITO` (`idTB_DEPOSITO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_MOSTRO_DEPOSITO_1`
    FOREIGN KEY (`fk_id_funcionario`)
    REFERENCES `mydb`.`TB_FUNCIONARIO` (`idTB_FUNCIONARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TB_VINHO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TB_VINHO` (
  `idTB_VINHO` INT NOT NULL AUTO_INCREMENT,
  `nome_vinho` VARCHAR(45) NOT NULL,
  `tipo_vinho` VARCHAR(45) NOT NULL,
  `isValid` TINYINT NOT NULL,
  PRIMARY KEY (`idTB_VINHO`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TB_PE_DE_CUBA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TB_PE_DE_CUBA` (
  `idTB_PE_DE_CUBA` INT NOT NULL AUTO_INCREMENT,
  `fk_id_funcionario` INT NOT NULL,
  `dataFimFermentacao_peDeCuba` DATE NULL,
  `dataInicio_peDeCuba` DATE NULL,
  `volume_peDeCuba` FLOAT NOT NULL,
  `isValid` TINYINT NOT NULL,
  PRIMARY KEY (`idTB_PE_DE_CUBA`),
  INDEX `fk_idUsuario_idx` (`fk_id_funcionario` ASC) VISIBLE,
  CONSTRAINT `fk_idUsuarioPeDeCuba`
    FOREIGN KEY (`fk_id_funcionario`)
    REFERENCES `mydb`.`TB_FUNCIONARIO` (`idTB_FUNCIONARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TB_VINHO_FERMENTACAO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TB_VINHO_FERMENTACAO` (
  `idTB_VINHO_FERMENTACAO` INT NOT NULL AUTO_INCREMENT,
  `fk_id_vinho` INT NOT NULL,
  `dataFimFermentacao_vinhoFermentacao` DATE NULL,
  `fk_id_mostro` INT NULL,
  `fk_id_peDeCuba` INT NULL,
  `volume_vinhoFermentacao` FLOAT NOT NULL,
  `isValid` TINYINT NOT NULL,
  PRIMARY KEY (`idTB_VINHO_FERMENTACAO`),
  INDEX `fk_idVinho_idx` (`fk_id_vinho` ASC) VISIBLE,
  INDEX `fk_idMostroVinhoFermentacao_idx` (`fk_id_mostro` ASC) VISIBLE,
  INDEX `fk_idPeDeCubaVinhoFermentacao_idx` (`fk_id_peDeCuba` ASC) VISIBLE,
  CONSTRAINT `fk_idVinho`
    FOREIGN KEY (`fk_id_vinho`)
    REFERENCES `mydb`.`TB_VINHO` (`idTB_VINHO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idMostroVinhoFermentacao`
    FOREIGN KEY (`fk_id_mostro`)
    REFERENCES `mydb`.`TB_MOSTRO` (`idTB_MOSTRO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idPeDeCubaVinhoFermentacao`
    FOREIGN KEY (`fk_id_peDeCuba`)
    REFERENCES `mydb`.`TB_PE_DE_CUBA` (`idTB_PE_DE_CUBA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TB_DEPOSITO_VINHO_FERMENTACAO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TB_DEPOSITO_VINHO_FERMENTACAO` (
  `idTB_DEPOSITO_VINHO_FERMENTACAO` INT NOT NULL AUTO_INCREMENT,
  `fk_id_vinhoFermentacao` INT NOT NULL,
  `fk_id_deposito` INT NOT NULL,
  `dataInicio_depositoVinhoFermentacao` DATE NOT NULL,
  `dataFim_depositoVinhoFermentacao` DATE NULL,
  `fk_id_funcionario` INT NOT NULL,
  PRIMARY KEY (`idTB_DEPOSITO_VINHO_FERMENTACAO`),
  INDEX `fk_idVinhoFermentacao_idx` (`fk_id_vinhoFermentacao` ASC) VISIBLE,
  INDEX `fk_idDeposito_idx` (`fk_id_deposito` ASC) VISIBLE,
  INDEX `fk_TB_DEPOSITO_VINHO_FERMENTACAO_1_idx` (`fk_id_funcionario` ASC) VISIBLE,
  CONSTRAINT `fk_idVinhoFermentacao`
    FOREIGN KEY (`fk_id_vinhoFermentacao`)
    REFERENCES `mydb`.`TB_VINHO_FERMENTACAO` (`idTB_VINHO_FERMENTACAO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idDepositoVinhoFermentacao`
    FOREIGN KEY (`fk_id_deposito`)
    REFERENCES `mydb`.`TB_DEPOSITO` (`idTB_DEPOSITO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_DEPOSITO_VINHO_FERMENTACAO_1`
    FOREIGN KEY (`fk_id_funcionario`)
    REFERENCES `mydb`.`TB_FUNCIONARIO` (`idTB_FUNCIONARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TB_PRODUTO_ADICIONADO_PEDECUBA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TB_PRODUTO_ADICIONADO_PEDECUBA` (
  `idTB_PRODUTO_ADICIONADO_PEDECUBA` INT NOT NULL AUTO_INCREMENT,
  `fk_id_peDeCuba` INT NOT NULL,
  `nome_produtoAdicionado` VARCHAR(45) NOT NULL,
  `quantidade_produtoAdicionado` FLOAT NOT NULL,
  PRIMARY KEY (`idTB_PRODUTO_ADICIONADO_PEDECUBA`),
  INDEX `fk_idPeDeCuba_idx` (`fk_id_peDeCuba` ASC) VISIBLE,
  CONSTRAINT `fk_idPeDeCubaProdutoAdicionado`
    FOREIGN KEY (`fk_id_peDeCuba`)
    REFERENCES `mydb`.`TB_PE_DE_CUBA` (`idTB_PE_DE_CUBA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TB_ANALISE_DIARIA_PEDECUBA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TB_ANALISE_DIARIA_PEDECUBA` (
  `idTB_ANALISES_DIARIAS` INT NOT NULL AUTO_INCREMENT,
  `fk_id_peDeCuba` INT NOT NULL,
  `fk_id_funcionario` INT NOT NULL,
  `densidade_analisePeDeCuba` FLOAT NOT NULL,
  `data_analisePeDeCuba` DATE NOT NULL,
  `temperatura_analisePeDeCuba` INT NOT NULL,
  PRIMARY KEY (`idTB_ANALISES_DIARIAS`),
  INDEX `fk_idFuncionario_idx` (`fk_id_funcionario` ASC) VISIBLE,
  INDEX `fk_idPeDeCuba_idx` (`fk_id_peDeCuba` ASC) VISIBLE,
  CONSTRAINT `fk_idFuncionarioAnalisePeDeCuba`
    FOREIGN KEY (`fk_id_funcionario`)
    REFERENCES `mydb`.`TB_FUNCIONARIO` (`idTB_FUNCIONARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idPeDeCubaAnalisePeDeCuba`
    FOREIGN KEY (`fk_id_peDeCuba`)
    REFERENCES `mydb`.`TB_PE_DE_CUBA` (`idTB_PE_DE_CUBA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TB_ANALISE_DIARIA_VINHOFERMENTACAO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TB_ANALISE_DIARIA_VINHOFERMENTACAO` (
  `idTB_ANALISES_DIARIAS` INT NOT NULL AUTO_INCREMENT,
  `fk_id_vinhoFermentacao` INT NOT NULL,
  `fk_id_funcionario` INT NOT NULL,
  `densidade_analiseVinhoFermentacao` FLOAT NOT NULL,
  `data_analiseVinhoFermentacao` DATE NOT NULL,
  `temperatura_analiseVinhoFermentacao` INT NOT NULL,
  `pressao_analiseVinhoFermentacao` FLOAT NULL,
  PRIMARY KEY (`idTB_ANALISES_DIARIAS`),
  INDEX `fk_idFuncionario_idx` (`fk_id_funcionario` ASC) VISIBLE,
  INDEX `fk_idVinhoFermentacao_idx` (`fk_id_vinhoFermentacao` ASC) VISIBLE,
  CONSTRAINT `fk_idFuncionarioAnaliseVinhoFermentacao`
    FOREIGN KEY (`fk_id_funcionario`)
    REFERENCES `mydb`.`TB_FUNCIONARIO` (`idTB_FUNCIONARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idVinhoFermentacaoAnaliseVinhoFermentacao`
    FOREIGN KEY (`fk_id_vinhoFermentacao`)
    REFERENCES `mydb`.`TB_VINHO_FERMENTACAO` (`idTB_VINHO_FERMENTACAO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TB_ANALISE_DIARIA_MOSTRO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TB_ANALISE_DIARIA_MOSTRO` (
  `idTB_ANALISES_DIARIAS` INT NOT NULL AUTO_INCREMENT,
  `fk_id_mostro` INT NOT NULL,
  `fk_id_funcionario` INT NOT NULL,
  `densidade_analiseMostro` FLOAT NOT NULL,
  `data_analiseMostro` DATE NOT NULL,
  `temperatura_analiseMostro` INT NOT NULL,
  PRIMARY KEY (`idTB_ANALISES_DIARIAS`),
  INDEX `fk_idFuncionario_idx` (`fk_id_funcionario` ASC) VISIBLE,
  INDEX `fk_idMostroAnaliseMostro_idx` (`fk_id_mostro` ASC) VISIBLE,
  CONSTRAINT `fk_idFuncionarioAnaliseMostro`
    FOREIGN KEY (`fk_id_funcionario`)
    REFERENCES `mydb`.`TB_FUNCIONARIO` (`idTB_FUNCIONARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idMostroAnaliseMostro`
    FOREIGN KEY (`fk_id_mostro`)
    REFERENCES `mydb`.`TB_MOSTRO` (`idTB_MOSTRO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TB_PRODUTO_ADICIONADO_VINHOFERMENTACAO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TB_PRODUTO_ADICIONADO_VINHOFERMENTACAO` (
  `idTB_PRODUTO_ADICIONADO_VINHOFERMENTACAO` INT NOT NULL AUTO_INCREMENT,
  `fk_id_vinhoFermentacao` INT NOT NULL,
  `nome_produtoAdicionado` VARCHAR(45) NOT NULL,
  `quantidade_produtoAdicionado` FLOAT NOT NULL,
  PRIMARY KEY (`idTB_PRODUTO_ADICIONADO_VINHOFERMENTACAO`),
  INDEX `fk_idVinhoFermentacao_idx` (`fk_id_vinhoFermentacao` ASC) VISIBLE,
  CONSTRAINT `fk_idVinhoFermentacaoProdutoAdicionado`
    FOREIGN KEY (`fk_id_vinhoFermentacao`)
    REFERENCES `mydb`.`TB_VINHO_FERMENTACAO` (`idTB_VINHO_FERMENTACAO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TB_DEPOSITO_PEDECUBA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TB_DEPOSITO_PEDECUBA` (
  `idTB_DEPOSITO_PEDECUBA` INT NOT NULL AUTO_INCREMENT,
  `fk_id_peDeCuba` INT NOT NULL,
  `fk_id_deposito` INT NOT NULL,
  `dataInicio_depositoPeDeCuba` DATE NOT NULL,
  `dataFim_depositoPeDeCuba` DATE NULL,
  `fk_id_funcionario` INT NOT NULL,
  PRIMARY KEY (`idTB_DEPOSITO_PEDECUBA`),
  INDEX `fk_idDeposito_idx` (`fk_id_deposito` ASC) VISIBLE,
  INDEX `fk_idPeDeCuba_idx` (`fk_id_peDeCuba` ASC) VISIBLE,
  INDEX `fk_TB_DEPOSITO_PEDECUBA_1_idx` (`fk_id_funcionario` ASC) VISIBLE,
  CONSTRAINT `fk_idPeDeCubaDeposito`
    FOREIGN KEY (`fk_id_peDeCuba`)
    REFERENCES `mydb`.`TB_PE_DE_CUBA` (`idTB_PE_DE_CUBA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idDepositoPedeCuba`
    FOREIGN KEY (`fk_id_deposito`)
    REFERENCES `mydb`.`TB_DEPOSITO` (`idTB_DEPOSITO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_DEPOSITO_PEDECUBA_1`
    FOREIGN KEY (`fk_id_funcionario`)
    REFERENCES `mydb`.`TB_FUNCIONARIO` (`idTB_FUNCIONARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TB_ANALISE_PRE_FERMENTACAO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TB_ANALISE_PRE_FERMENTACAO` (
  `idTB_ANALISE_PRE_FERMENTACAO` INT NOT NULL,
  `fk_id_vinhoFermentacao` INT NOT NULL,
  `aTotal_vinhoFermentacao` VARCHAR(45) NOT NULL,
  `acucarRed_vinhoFermentacao` VARCHAR(45) NOT NULL,
  `ph_vinhoFermentacao` VARCHAR(45) NOT NULL,
  `so2l_vinhoFermentacao` VARCHAR(45) NOT NULL,
  `ta_vinhoFermentacao` VARCHAR(45) NOT NULL,
  `fk_id_funcionario` INT NULL,
  PRIMARY KEY (`idTB_ANALISE_PRE_FERMENTACAO`),
  INDEX `fk_TB_ANALISE_PRE_FERMENTACAO_1_idx` (`fk_id_vinhoFermentacao` ASC) VISIBLE,
  INDEX `fk_TB_ANALISE_PRE_FERMENTACAO_2_idx` (`fk_id_funcionario` ASC) VISIBLE,
  CONSTRAINT `fk_TB_ANALISE_PRE_FERMENTACAO_1`
    FOREIGN KEY (`fk_id_vinhoFermentacao`)
    REFERENCES `mydb`.`TB_VINHO_FERMENTACAO` (`idTB_VINHO_FERMENTACAO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_ANALISE_PRE_FERMENTACAO_2`
    FOREIGN KEY (`fk_id_funcionario`)
    REFERENCES `mydb`.`TB_FUNCIONARIO` (`idTB_FUNCIONARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
