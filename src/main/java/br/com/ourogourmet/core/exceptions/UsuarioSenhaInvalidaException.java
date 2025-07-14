package br.com.ourogourmet.core.exceptions;

public class UsuarioSenhaInvalidaException extends RuntimeException {
    public UsuarioSenhaInvalidaException() {
        super("Senha informada está incorreta.");
    }
}
