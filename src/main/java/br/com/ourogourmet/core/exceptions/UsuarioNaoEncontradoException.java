package br.com.ourogourmet.core.exceptions;


public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(){
        super("Usuário não encontrado");
    }
}
