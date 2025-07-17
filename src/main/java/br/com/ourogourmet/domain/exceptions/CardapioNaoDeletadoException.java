package br.com.ourogourmet.domain.exceptions;

public class CardapioNaoDeletadoException extends RuntimeException {
    public CardapioNaoDeletadoException(String id) {
        super("Não foi possível deletar o cardapio. ID: " + id);
    }

}
