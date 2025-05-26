package br.com.ourogourmet.ouro.gourmet.usuario.exceptions;

import java.util.Set;
import java.util.stream.Collectors;

public class UsuarioCriacaoFalhaException extends RuntimeException {
    public UsuarioCriacaoFalhaException(Set<String> violations) {
        super("Falha ao criar usuário: " + violations.stream().collect(Collectors.joining(" - ")));
    }

    public UsuarioCriacaoFalhaException(String message) {
        super("Falha ao criar usuário: " + message);
    }

}
