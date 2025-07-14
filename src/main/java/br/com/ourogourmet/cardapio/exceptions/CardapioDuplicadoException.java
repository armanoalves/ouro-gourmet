package br.com.ourogourmet.cardapio.exceptions;

public class CardapioDuplicadoException extends RuntimeException {
    public CardapioDuplicadoException(String duplicado) {
        super("A informação " + duplicado + " já existe no banco");
    }
}