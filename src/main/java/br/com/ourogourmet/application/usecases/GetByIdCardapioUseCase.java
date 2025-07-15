package br.com.ourogourmet.application.usecases;

import br.com.ourogourmet.core.entities.Cardapio;

public interface GetByIdCardapioUseCase {
    Cardapio findById(String id);
}
