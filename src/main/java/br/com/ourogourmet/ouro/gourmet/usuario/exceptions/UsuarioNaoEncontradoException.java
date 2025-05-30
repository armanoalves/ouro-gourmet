package br.com.ourogourmet.ouro.gourmet.usuario.exceptions;


public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(){
        super("Usuário não encontrado");
    }
}
