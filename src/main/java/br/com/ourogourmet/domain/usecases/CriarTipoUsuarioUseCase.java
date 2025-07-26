package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.entities.enums.TipoUsuarioEnum;

public interface CriarTipoUsuarioUseCase {
    Long execute(CriarTipoUsuarioCommand usuario);

    record CriarTipoUsuarioCommand(
            TipoUsuarioEnum tipoUsuarioEnum){
    }
}
