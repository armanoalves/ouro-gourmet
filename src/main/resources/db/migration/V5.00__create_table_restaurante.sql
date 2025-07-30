CREATE TABLE restaurante
(
	id BIGINT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(255) NOT NULL,
	tipo_cozinha VARCHAR(255),
	usuario_id VARCHAR(36),
	horario_funcionamento_de TIME,
	horario_funcionamento_ate TIME,

    CONSTRAINT restaurante_pkey PRIMARY KEY (id)
);

CREATE UNIQUE INDEX idx_nome ON restaurante (nome);

