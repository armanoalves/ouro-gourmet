package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.exceptions.RestauranteNaoEncontradoException;
import br.com.ourogourmet.domain.gateway.RestauranteGateway;
import br.com.ourogourmet.domain.gateway.UsuarioGateway;
import br.com.ourogourmet.domain.usecases.AlterarRestauranteUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class AlterarRestauranteService implements AlterarRestauranteUseCase {

    private final UsuarioGateway usuarioRepository;
    @Override
    public void execute(AlterarRestauranteCommand restauranteCommand,
                        RestauranteGateway restauranteRepository) {

        var restaurante = restauranteRepository.buscarPorId(restauranteCommand.id())
                .orElseThrow(()-> new RestauranteNaoEncontradoException(restauranteCommand.id().toString()));

        restauranteRepository.buscarPorNome(restauranteCommand.nome())
                .ifPresent(restauranteNomeRepetido->{
                    if (!restauranteNomeRepetido.getId().equals(restauranteCommand.id()))
                        throw new RestauranteNaoEncontradoException(restauranteCommand.nome());
                });

        restaurante.setNome(restauranteCommand.nome());
        restaurante.setTipoCozinha(restauranteCommand.tipoCozinha());
        restaurante.setHorarioFuncionamento(restauranteCommand.horarioFuncionamentoDe(),restauranteCommand.horarioFuncionamentoAte());
        restauranteRepository.alterar(restaurante);

    }
}
