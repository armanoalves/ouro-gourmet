package br.com.ourogourmet.ouro.gourmet.usuario.exceptions;

import java.util.Set;
import java.util.stream.Collectors;

public class UsuarioAlteracaoInvalidaException extends RuntimeException {
    public UsuarioAlteracaoInvalidaException(Set<String> violations) {
        super("Alteração inválida: " + violations.stream().collect(Collectors.joining(" - ")));
    }

    public UsuarioAlteracaoInvalidaException(String message) {
        super(message);
    }
}
