package br.com.ourogourmet.application.interfaces;

import br.com.ourogourmet.application.usecases.AlterarTipoUsuarioUseCase.AlterarTipoUsuarioCommand;
import br.com.ourogourmet.core.entities.TipoUsuario;

import java.util.List;
import java.util.Optional;

public interface TipoUsuarioGateway {

    Long incluir (TipoUsuario tipoUsuario);
    List<TipoUsuario> findAll(int size, int offset);
    TipoUsuario findById(String Id);
    void alterar(AlterarTipoUsuarioCommand alterarTipoUsuarioCommand);
    Integer delete(String id);
}
