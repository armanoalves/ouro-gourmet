package br.com.ourogourmet.core.exceptions;

public class TipoUsuarioNaoEncontradoException extends RuntimeException {
    public TipoUsuarioNaoEncontradoException() {
        super("TipoUsuario não encontrado");
    }
}
