package br.com.ourogourmet.domain.exceptions;


public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(){
        super("Usuário não encontrado");
    }
}
