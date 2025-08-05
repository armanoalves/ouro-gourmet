package br.com.ourogourmet.domain.entities;

import lombok.Getter;

@Getter
public class TipoUsuario {

    private Long id;
    private String tipo;

    protected TipoUsuario() {}

    private TipoUsuario(String tipo, Long id) {
        this.id = id;
        this.tipo = tipo;
    }

    public static TipoUsuario create(String tipo) {
        var tipoUsuario = new TipoUsuario();

        tipoUsuario.setTipo(tipo.toUpperCase());
        return tipoUsuario;
    }

    public static TipoUsuario create(Long id, String tipo) {
        return new TipoUsuario(tipo, id);
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}