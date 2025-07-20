package br.com.ourogourmet.core.exceptions;

public class TipoUsuarioNaoDeletadoException extends RuntimeException {
    public TipoUsuarioNaoDeletadoException(String id) {
        super("Não foi possível deletar o tipoUsuario. ID: " + id);
    }
}
