package br.com.ourogourmet.core.exceptions;

public class RestauranteNaoEncontradoException extends RuntimeException {
    public RestauranteNaoEncontradoException(String message) {
        super( "Restaurante não encontrado: " + message);
    }
}
