package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.exceptions.RestauranteNaoEncontradoException;
import br.com.ourogourmet.domain.gateway.RestauranteGateway;
import br.com.ourogourmet.domain.gateway.UsuarioGateway;
import br.com.ourogourmet.domain.usecases.AlterarRestauranteUsuarioUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class AlterarRestauranteUsuarioService implements AlterarRestauranteUsuarioUseCase {

    private final UsuarioGateway usuarioRepository;

    @Override
    public void execute(AlterarRestauranteUsuarioCommand restauranteCommand, RestauranteGateway restauranteRepository) {

        var restaurante = restauranteRepository.buscarPorId(restauranteCommand.id())
                .orElseThrow(()-> new RestauranteNaoEncontradoException(restauranteCommand.id().toString()));

        restaurante.setUsuario( nonNull(restauranteCommand.usuarioId()) ? usuarioRepository.findById(restauranteCommand.usuarioId()) : null );

        restauranteRepository.alterar(restaurante);

    }
}
