package br.com.ourogourmet.ouro.gourmet.usuario.exceptions;

public class UsuarioEmailJaExistenteException extends RuntimeException {
    public UsuarioEmailJaExistenteException(String email) {
        super("E-mail já cadastrado: " + email);
    }
}