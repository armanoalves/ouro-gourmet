package br.com.ourogourmet.domain.exceptions;

public class UsuarioSenhaInvalidaException extends RuntimeException {
    public UsuarioSenhaInvalidaException() {
        super("Senha informada está incorreta.");
    }
}
