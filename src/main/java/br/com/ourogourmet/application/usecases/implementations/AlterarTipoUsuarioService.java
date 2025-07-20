package br.com.ourogourmet.application.usecases.implementations;

import br.com.ourogourmet.application.interfaces.TipoUsuarioGateway;
import br.com.ourogourmet.application.usecases.AlterarTipoUsuarioUseCase;
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
