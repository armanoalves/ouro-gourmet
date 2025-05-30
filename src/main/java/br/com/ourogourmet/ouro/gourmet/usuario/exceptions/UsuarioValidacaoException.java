package br.com.ourogourmet.ouro.gourmet.usuario.exceptions;

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
