package br.com.ourogourmet.cardapio.usecase;

import br.com.ourogourmet.cardapio.entities.Cardapio;

public interface GetByIdCardapioUseCase {
    Cardapio findById(String id);
}
