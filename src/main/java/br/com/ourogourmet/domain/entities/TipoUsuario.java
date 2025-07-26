package br.com.ourogourmet.domain.entities;

import br.com.ourogourmet.domain.entities.enums.TipoUsuarioEnum;

public class TipoUsuario {

    private Long id;
    private TipoUsuarioEnum tipo;

    protected TipoUsuario() {}

    public static TipoUsuario create(TipoUsuarioEnum tipo) {

        var tipoUsuario = new TipoUsuario();

        tipoUsuario.setTipo(tipo);
        return tipoUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoUsuarioEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuarioEnum tipo) {
        this.tipo = tipo;
    }
}