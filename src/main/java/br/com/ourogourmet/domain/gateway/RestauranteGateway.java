package br.com.ourogourmet.domain.gateway;

import br.com.ourogourmet.domain.usecases.AlterarRestauranteUseCase.AlterarRestauranteCommand;
import br.com.ourogourmet.domain.entities.Restaurante;

import java.util.Optional;

public interface RestauranteGateway {

    Long incluir(Restaurante restaurante);
    Optional<Restaurante> buscarPorId(Long id);
    Optional<Restaurante> findByNome(String nome);
    void alterar(AlterarRestauranteCommand alterarRestauranteCommand);
}
