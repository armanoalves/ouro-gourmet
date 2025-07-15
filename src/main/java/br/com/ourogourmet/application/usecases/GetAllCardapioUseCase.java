package br.com.ourogourmet.application.usecases;

import br.com.ourogourmet.core.entities.Cardapio;

import java.util.List;

public interface GetAllCardapioUseCase {
    List<Cardapio> findAll(int page, int size);
}
