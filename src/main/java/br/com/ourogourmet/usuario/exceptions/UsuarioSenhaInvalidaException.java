package br.com.ourogourmet.usuario.exceptions;

public class UsuarioSenhaInvalidaException extends RuntimeException {
    public UsuarioSenhaInvalidaException() {
        super("Senha informada está incorreta.");
    }
}
