package br.com.ourogourmet.application.interfaces;

import br.com.ourogourmet.application.usecases.AlterarRestauranteUseCase.AlterarRestauranteCommand;
import br.com.ourogourmet.core.entities.Restaurante;

import java.util.Optional;

public interface RestauranteGateway {

    Long incluir(Restaurante restaurante);
    Optional<Restaurante> buscarPorId(Long id);
    Optional<Restaurante> findByNome(String nome);
    void alterar(AlterarRestauranteCommand alterarRestauranteCommand);
}
