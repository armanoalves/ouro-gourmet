package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.gateway.TipoUsuarioGateway;
import br.com.ourogourmet.domain.usecases.AlterarTipoUsuarioUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AlterarTipoUsuarioService implements AlterarTipoUsuarioUseCase {

    private final TipoUsuarioGateway tipoUsuarioGateway;

    @Override
    public void execute(AlterarTipoUsuarioCommand tipoUsuarioCommand) {
        tipoUsuarioGateway.alterar(tipoUsuarioCommand);
    }
}
