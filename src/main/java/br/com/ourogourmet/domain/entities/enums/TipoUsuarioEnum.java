package br.com.ourogourmet.domain.entities.enums;

import lombok.Getter;

@Getter
public enum TipoUsuarioEnum {

    DONO("Dono de Restaurante"),
    CLIENTE("Cliente");

    private final String descricao;

    TipoUsuarioEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
