package br.com.ourogourmet.domain.exceptions;

public class UsuarioNaoDeletadoException extends RuntimeException {
    public UsuarioNaoDeletadoException(String id) {
        super("Não foi possível deletar o usuário. ID: " + id);
    }

}
