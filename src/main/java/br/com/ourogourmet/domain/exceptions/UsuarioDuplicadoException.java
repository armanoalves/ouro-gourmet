package br.com.ourogourmet.domain.exceptions;

public class UsuarioDuplicadoException extends RuntimeException {
    public UsuarioDuplicadoException(String duplicado) {
        super("A informação " + duplicado + " já existe no banco");
    }
}