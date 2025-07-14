package br.com.ourogourmet.cardapio.usecase;

import br.com.ourogourmet.cardapio.entities.Cardapio;

import java.util.List;

public interface GetAllCardapioUseCase {
    List<Cardapio> findAll(int page, int size);
}
