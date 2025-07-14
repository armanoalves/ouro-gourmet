package br.com.ourogourmet.cardapio.exceptions;


public class CardapioNaoEncontradoException extends RuntimeException {
    public CardapioNaoEncontradoException(){
        super("Cardapio não encontrado");
    }
}
