package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.entities.Cardapio;

public interface GetByIdCardapioUseCase {
    Cardapio findById(Long id);
}
