package br.com.ourogourmet.ouro.gourmet.usuario.exceptions;

import java.util.Set;
import java.util.stream.Collectors;

public class UsuarioCamposInvalidosException extends RuntimeException {
    public UsuarioCamposInvalidosException(Set<String> violations) {
        super(violations.stream().collect(Collectors.joining(" | ")));
    }
}
