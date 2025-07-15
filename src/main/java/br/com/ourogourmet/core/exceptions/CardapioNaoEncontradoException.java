package br.com.ourogourmet.core.exceptions;


public class CardapioNaoEncontradoException extends RuntimeException {
    public CardapioNaoEncontradoException(){
        super("Cardapio não encontrado");
    }
}
