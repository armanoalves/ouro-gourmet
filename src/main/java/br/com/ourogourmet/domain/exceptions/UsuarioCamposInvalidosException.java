package br.com.ourogourmet.domain.exceptions;

import java.util.Set;
import java.util.stream.Collectors;

public class UsuarioCamposInvalidosException extends RuntimeException {
    public UsuarioCamposInvalidosException(Set<String> violations) {
        super(violations.stream().collect(Collectors.joining(" | ")));
    }
}
