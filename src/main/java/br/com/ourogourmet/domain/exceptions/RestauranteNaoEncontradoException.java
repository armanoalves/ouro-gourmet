package br.com.ourogourmet.domain.exceptions;

public class RestauranteNaoEncontradoException extends RuntimeException {
    public RestauranteNaoEncontradoException(String message) {
        super( "Restaurante não encontrado: " + message);
    }
}
