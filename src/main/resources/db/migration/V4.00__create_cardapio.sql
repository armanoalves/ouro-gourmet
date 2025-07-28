CREATE TABLE cardapio
(
    id BIGINT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(255),
	descricao VARCHAR(255),
    preco double(10, 2),
    foto VARCHAR(150),
	consumo_local BOOLEAN,
    data_criacao TIMESTAMP,
    data_alteracao TIMESTAMP,
    CONSTRAINT cardapio_pkey PRIMARY KEY (id)
);
