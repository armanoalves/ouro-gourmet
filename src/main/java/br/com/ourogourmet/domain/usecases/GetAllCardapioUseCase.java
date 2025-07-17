package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.entities.Cardapio;

import java.util.List;

public interface GetAllCardapioUseCase {
    List<Cardapio> findAll(int page, int size);
}
