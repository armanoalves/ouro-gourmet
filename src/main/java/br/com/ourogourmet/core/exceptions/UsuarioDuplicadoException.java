package br.com.ourogourmet.core.exceptions;

public class UsuarioDuplicadoException extends RuntimeException {
    public UsuarioDuplicadoException(String duplicado) {
        super("A informação " + duplicado + " já existe no banco");
    }
}