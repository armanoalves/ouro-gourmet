package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.entities.enums.TipoUsuarioEnum;

public interface AlterarTipoUsuarioUseCase {

    void execute(AlterarTipoUsuarioCommand tipoUsuarioCommand);

    record AlterarTipoUsuarioCommand(
            Long id,
            String tipo){
    }
}
