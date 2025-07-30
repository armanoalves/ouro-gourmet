CREATE TABLE usuario
(
    id CHAR(36) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    login VARCHAR(50) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    senha VARCHAR(255) NOT NULL,
    data_alteracao DATE,
    endereco VARCHAR(255),
    tipo_usuario_id BIGINT NOT NULL,
    CONSTRAINT usuario_pkey PRIMARY KEY (id),
    CONSTRAINT fk_tipo_usuario FOREIGN KEY (tipo_usuario_id)
        REFERENCES tipo_usuario(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);