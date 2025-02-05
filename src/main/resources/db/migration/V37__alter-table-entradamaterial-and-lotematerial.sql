ALTER TABLE tb_entradamaterial ADD COLUMN `fklotematerial` INT NOT NULL;
ALTER TABLE tb_entradamaterial CHANGE COLUMN `identrada_material` `id` INT NOT NULL AUTO_INCREMENT;
ALTER TABLE tb_entradamaterial ADD CONSTRAINT `fk_entradamaterial_lotematerial` FOREIGN KEY (`fklotematerial`) REFERENCES tb_lotematerial(`id`);

ALTER TABLE tb_lotematerial DROP FOREIGN KEY fk_tb_lotematerial_1;
ALTER TABLE tb_lotematerial DROP COLUMN `fkmaterial`;

