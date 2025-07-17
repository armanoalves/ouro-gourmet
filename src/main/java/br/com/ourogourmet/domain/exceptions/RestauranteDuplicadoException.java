package br.com.ourogourmet.domain.exceptions;

public class RestauranteDuplicadoException extends RuntimeException {
    public RestauranteDuplicadoException(String message) {
        super(message);
    }
}
