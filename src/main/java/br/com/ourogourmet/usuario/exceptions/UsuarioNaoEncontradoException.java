package br.com.ourogourmet.usuario.exceptions;


public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(){
        super("Usuário não encontrado");
    }
}
