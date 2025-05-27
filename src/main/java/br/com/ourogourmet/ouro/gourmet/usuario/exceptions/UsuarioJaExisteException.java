package br.com.ourogourmet.ouro.gourmet.usuario.exceptions;

public class UsuarioJaExisteException extends RuntimeException {
    public UsuarioJaExisteException(String mensagem) {
        super(mensagem);
    }
}
