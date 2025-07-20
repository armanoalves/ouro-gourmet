package br.com.ourogourmet.core.exceptions;

import java.util.Set;
import java.util.stream.Collectors;

public class TipoUsuarioCamposInvalidosException extends RuntimeException {
    public TipoUsuarioCamposInvalidosException(Set<String> violations) {
        super(violations.stream().collect(Collectors.joining(" | ")));
    }
}
