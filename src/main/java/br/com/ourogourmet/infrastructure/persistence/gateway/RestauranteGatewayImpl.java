package br.com.ourogourmet.infrastructure.persistence.gateway;

import br.com.ourogourmet.application.interfaces.RestauranteGateway;
import br.com.ourogourmet.application.usecases.AlterarRestauranteUseCase.AlterarRestauranteCommand;
import br.com.ourogourmet.core.entities.Restaurante;
import br.com.ourogourmet.core.exceptions.RestauranteNaoEncontradoException;
import br.com.ourogourmet.infrastructure.persistence.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestauranteGatewayImpl extends JpaRepository<RestauranteEntity, Long>,RestauranteGateway
{

    @Override
    default Long incluir(Restaurante restaurante) {
        var entity = new RestauranteEntity(restaurante);
        return this.save(entity).getId();
    }

    @Override
    default Optional<Restaurante> buscarPorId(Long id) {

        var optionalRestaurante = this.findById(id);

        if (optionalRestaurante.isPresent()){
            var restaurante = optionalRestaurante.get();
            return Optional.of(restaurante.toDomain());
        }

        return Optional.empty();
    }

    @Override
    default void alterar(AlterarRestauranteCommand restaurante){

        this.findById(restaurante.id()).ifPresentOrElse(restauranteAlterado->{
            restauranteAlterado.setNome(restaurante.nome());
            restauranteAlterado.setTipoCozinha(restaurante.tipoCozinha());
            restauranteAlterado.setHorarioFuncionamentoDe(restaurante.horarioFuncionamentoDe());
            restauranteAlterado.setHorarioFuncionamentoAte(restaurante.horarioFuncionamentoAte());
            this.save(restauranteAlterado);
        }, ()-> { throw new RestauranteNaoEncontradoException(String.valueOf(restaurante.id()));} );

    }
}
