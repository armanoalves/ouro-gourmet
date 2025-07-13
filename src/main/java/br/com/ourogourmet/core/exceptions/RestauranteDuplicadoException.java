package br.com.ourogourmet.core.exceptions;

public class RestauranteDuplicadoException extends RuntimeException {
    public RestauranteDuplicadoException(String message) {
        super(message);
    }
}
