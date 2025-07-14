package br.com.ourogourmet.application.usecases;

import br.com.ourogourmet.core.entities.Restaurante;

import java.util.List;

public interface GetAllRestauranteUseCase {
    List<Restaurante> findAll(int page, int size);
}
