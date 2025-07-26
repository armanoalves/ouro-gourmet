package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.entities.TipoUsuario;
import br.com.ourogourmet.domain.gateway.TipoUsuarioGateway;
import br.com.ourogourmet.domain.usecases.CriarTipoUsuarioUseCase;
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
