package br.com.ourogourmet.core.exceptions;

public class TipoUsuarioNaoEncontradoException extends RuntimeException {

    public TipoUsuarioNaoEncontradoException() {
        super("Tipo de usuário não encontrado.");
    }

    public TipoUsuarioNaoEncontradoException(String id) {
        super("Tipo de usuário não encontrado com ID: " + id);
    }
}
