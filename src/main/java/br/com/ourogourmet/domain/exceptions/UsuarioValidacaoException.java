package br.com.ourogourmet.domain.exceptions;

import java.util.Set;
import java.util.stream.Collectors;

public class UsuarioValidacaoException extends RuntimeException {
    public UsuarioValidacaoException(Set<String> violations) {
        super("Alteração inválida: " + violations.stream().collect(Collectors.joining(" - ")));
    }
    public UsuarioValidacaoException(String message) {
        super(message);
    }
}
