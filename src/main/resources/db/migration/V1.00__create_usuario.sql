CREATE TABLE usuario
(
	id VARCHAR(36),
	email VARCHAR(255),
	login VARCHAR(36),
	ativo BOOLEAN,
    CONSTRAINT usuario_pkey PRIMARY KEY (id)
);
