package br.com.ourogourmet.application.usecases.implementations;

import br.com.ourogourmet.application.interfaces.TipoUsuarioGateway;
import br.com.ourogourmet.application.usecases.CriarTipoUsuarioUseCase;
import br.com.ourogourmet.core.entities.TipoUsuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CriarTipoUsuarioService implements CriarTipoUsuarioUseCase {

    private final TipoUsuarioGateway tipoUsuarioRepository;

    @Override
    public Long execute(CriarTipoUsuarioCommand tipocommand) {
        final TipoUsuario tipoUsuario = TipoUsuario.create(
                tipocommand.tipoUsuarioEnum());
        return this.tipoUsuarioRepository.incluir(tipoUsuario);
    }
}
