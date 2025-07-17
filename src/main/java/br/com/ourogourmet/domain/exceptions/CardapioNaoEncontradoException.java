package br.com.ourogourmet.domain.exceptions;


public class CardapioNaoEncontradoException extends RuntimeException {
    public CardapioNaoEncontradoException(){
        super("Cardapio não encontrado");
    }
}
