package br.com.ourogourmet.cardapio.exceptions;

import java.util.Set;
import java.util.stream.Collectors;

public class CardapioCriacaoFalhaException extends RuntimeException {
    public CardapioCriacaoFalhaException(Set<String> violations) {
        super("Falha ao criar usuário: " + violations.stream().collect(Collectors.joining(" - ")));
    }

    public CardapioCriacaoFalhaException(String message) {
        super("Falha ao criar usuário: " + message);
    }
}
