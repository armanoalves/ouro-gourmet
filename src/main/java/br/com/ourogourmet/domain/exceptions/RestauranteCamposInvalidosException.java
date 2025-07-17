package br.com.ourogourmet.domain.exceptions;

import java.util.Set;
import java.util.stream.Collectors;

public class RestauranteCamposInvalidosException extends RuntimeException {
    public RestauranteCamposInvalidosException(Set<String> violations) {
        super(violations.stream().collect(Collectors.joining(" | ")));
    }
}
