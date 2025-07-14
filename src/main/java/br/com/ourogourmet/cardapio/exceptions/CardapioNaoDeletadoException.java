package br.com.ourogourmet.cardapio.exceptions;

public class CardapioNaoDeletadoException extends RuntimeException {
    public CardapioNaoDeletadoException(String id) {
        super("Não foi possível deletar o cardapio. ID: " + id);
    }

}
