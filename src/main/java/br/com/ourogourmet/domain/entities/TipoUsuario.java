package br.com.ourogourmet.domain.entities;

import lombok.Getter;

@Getter
public class TipoUsuario {

    private Long id;
    private String tipo;

    protected TipoUsuario() {}

    public static TipoUsuario create(String tipo) {
        var tipoUsuario = new TipoUsuario();

        tipoUsuario.setTipo(tipo.toUpperCase());
        return tipoUsuario;
    }


    public void setId(Long id) {
        this.id = id;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}