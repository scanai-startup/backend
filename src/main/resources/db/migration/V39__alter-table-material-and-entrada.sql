ALTER TABLE tb_entradamaterial DROP FOREIGN KEY fk_entrada_material_1;
ALTER TABLE tb_entradamaterial DROP COLUMN `fkmaterial`;

ALTER TABLE tb_lotematerial ADD COLUMN `fkmaterial` INT NOT NULL;
ALTER TABLE tb_lotematerial ADD CONSTRAINT `fk_lotematerial_material` FOREIGN KEY (`fkmaterial`) REFERENCES tb_material(`id`);
