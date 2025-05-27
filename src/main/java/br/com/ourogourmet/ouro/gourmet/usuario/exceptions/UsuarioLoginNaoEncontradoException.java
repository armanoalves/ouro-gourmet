package br.com.ourogourmet.ouro.gourmet.usuario.exceptions;

public class UsuarioLoginNaoEncontradoException extends RuntimeException {
    public UsuarioLoginNaoEncontradoException(String login) {
        super("Login de usuário não encontrado: " + login);
    }
}
