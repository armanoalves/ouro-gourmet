package br.com.ourogourmet.ouro.gourmet.usuario.exceptions;

public class UsuarioNaoDeletadoException extends RuntimeException {
    public UsuarioNaoDeletadoException(String id) {
        super("Não foi possível deletar o usuário. ID: " + id);
    }

}
