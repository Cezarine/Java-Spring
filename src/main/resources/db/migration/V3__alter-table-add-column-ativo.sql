ALTER TABLE medicos ADD COLUMN ativo TINYINT(1) DEFAULT 1 NOT NULL AFTER cidade;
ALTER TABLE medicos CHANGE telefone telefone VARCHAR(20) NOT NULL AFTER especialidade; 