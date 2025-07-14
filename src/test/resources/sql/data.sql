CREATE TABLE usuario
(
    id VARCHAR(36),
    email VARCHAR(255),
    login VARCHAR(36),
    ativo BOOLEAN,
    senha VARCHAR(36),
    data_alteracao TIMESTAMP,
    endereco VARCHAR(36),
    CONSTRAINT usuario_pkey PRIMARY KEY (id)
);

INSERT INTO usuario (id, email, login, ativo, nome, senha, endereco)
VALUES ('20f8f6aa-6ff6-4701-8c8e-49000fa471d3', 'usuario01@gmail.com', 'usuario01',true,'Jose Augusto','senha1234','Rua XVII,15 - São Paulo, SP');

INSERT INTO usuario (id, email, login, ativo, nome, senha, endereco)
VALUES ('2fede0f9-c6c0-4533-8bb2-2ae4d702fc1g', 'usuario02@gmail.com', 'usuario02',true,'Amaral','senha9090','Rua São Caetano, 15 - São Paulo, SP');

CREATE TABLE cardapio
(
    id VARCHAR(36),
    nome VARCHAR(255),
    descricao VARCHAR(255),
    preco double(10, 2),
    foto VARCHAR(150),
    consumo_local BOOLEAN,
    data_criacao TIMESTAMP,
    data_alteracao TIMESTAMP,
    CONSTRAINT cardapio_pkey PRIMARY KEY (id)
);

INSERT INTO cardapio (id, nome, descricao, preco, foto, consumo_local)
VALUES ('9824c0c5-f89c-4490-9a4c-83a9d910949b', 'X-BURGUER', 'carne com queijo e pão',25, 'c:/', true);

INSERT INTO cardapio (id, nome, descricao, preco, foto, consumo_local)
VALUES ('9824c0c5-f89c-4490-9a4c-83a9d9109400', 'X-TUDO', 'carne, pão, queijo, tomate e ovo',35, 'c:/', true);