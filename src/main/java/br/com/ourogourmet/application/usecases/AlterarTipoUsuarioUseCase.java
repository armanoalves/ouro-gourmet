package br.com.ourogourmet.application.usecases;

import br.com.ourogourmet.core.entities.enums.TipoUsuarioEnum;

public interface AlterarTipoUsuarioUseCase {

    void execute(AlterarTipoUsuarioCommand tipoUsuarioCommand);

    record AlterarTipoUsuarioCommand(
            Long id,
            TipoUsuarioEnum tipo){
    }
}
