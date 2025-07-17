package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.entities.Restaurante;

import java.util.List;

public interface GetAllRestauranteUseCase {
    List<Restaurante> findAll(int page, int size);
}
