package br.com.ourogourmet.infrastructure.adapter.repository;

import br.com.ourogourmet.domain.entities.Restaurante;
import br.com.ourogourmet.domain.gateway.RestauranteGateway;
import br.com.ourogourmet.domain.usecases.AlterarRestauranteUseCase.AlterarRestauranteCommand;
import br.com.ourogourmet.infrastructure.model.RestauranteEntity;
import br.com.ourogourmet.infrastructure.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RestauranteEntityRepository implements RestauranteGateway {

    private final RestauranteRepository restauranteRepository;

    @Override
    public Long incluir(Restaurante restaurante) {
        var newRestaurante = this.restauranteRepository.save(new RestauranteEntity(restaurante));
        return newRestaurante.getId();
    }

    @Override
    public Optional<Restaurante> buscarPorId(Long id) {

        var restauranteEntity = this.restauranteRepository.findById(id);

        return Optional.ofNullable(restauranteEntity.get().toDomain());
    }

    @Override
    public Optional<Restaurante> findByNome(String nome) {
        return Optional.empty();
    }

    @Override
    public void alterar(AlterarRestauranteCommand alterarRestauranteCommand) {

    }
}
