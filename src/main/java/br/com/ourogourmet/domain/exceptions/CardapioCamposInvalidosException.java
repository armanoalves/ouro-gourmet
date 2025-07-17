package br.com.ourogourmet.domain.exceptions;

import java.util.Set;
import java.util.stream.Collectors;

public class CardapioCamposInvalidosException extends RuntimeException {
    public CardapioCamposInvalidosException(Set<String> violations) {
        super(violations.stream().collect(Collectors.joining(" | ")));
    }
}
