package br.com.ourogourmet.application.usecases;

import br.com.ourogourmet.core.entities.enums.TipoUsuarioEnum;

public interface CriarTipoUsuarioUseCase {
    Long execute(CriarTipoUsuarioCommand usuario);

    record CriarTipoUsuarioCommand(
            TipoUsuarioEnum tipoUsuarioEnum){
    }
}
