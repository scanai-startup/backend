CREATE TABLE IF NOT EXISTS tb_uva (
     `id` INT NOT NULL AUTO_INCREMENT COMMENT '	',
     `fkviticultor` INT NOT NULL,
     `fkfuncionario` INT NOT NULL,
     `datachegada` DATE,
     `numerotalao` INT NOT NULL COMMENT '	',
     `sanidade` INT NOT NULL,
     `peso` INT NOT NULL,
     `so2` VARCHAR(45) NOT NULL,
    `numerolote` INT NOT NULL,
    `tipovinho` VARCHAR(45) NOT NULL,
    `casta` VARCHAR(45) NOT NULL,
    `fkmostro` INT NULL,
    `valid` TINYINT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_IdViticultor_remessaUva_idx` (`fkviticultor` ASC) VISIBLE,
    INDEX `fk_idFuncionario_idx` (`fkfuncionario` ASC) VISIBLE,
    INDEX `fk_TB_REMESSA_UVA_1_idx` (`fkmostro` ASC) VISIBLE,
    CONSTRAINT `fk_IdViticultor_remessaUva`
    FOREIGN KEY (`fkviticultor`)
    REFERENCES `scanAi`.`tb_viticultor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_idFuncionarioRemeessaUva`
    FOREIGN KEY (`fkfuncionario`)
    REFERENCES `scanAi`.`tb_funcionario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_TB_REMESSA_UVA_5`
    FOREIGN KEY (`fkmostro`)
    REFERENCES `scanAi`.`tb_mostro` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)