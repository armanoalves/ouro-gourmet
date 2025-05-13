ALTER TABLE usuario
    ADD COLUMN nome VARCHAR(36),
    ADD COLUMN senha VARCHAR(36),
    ADD COLUMN data_alteracao TIMESTAMP,
    ADD COLUMN endereco VARCHAR(36);

