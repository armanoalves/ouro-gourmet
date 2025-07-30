package br.com.ourogourmet.domain.gateway;

import br.com.ourogourmet.domain.entities.TipoUsuario;
import br.com.ourogourmet.domain.usecases.AlterarTipoUsuarioUseCase;

import java.util.List;

public interface TipoUsuarioGateway {

    Long incluir (TipoUsuario tipoUsuario);
    List<TipoUsuario> findAll(int size, int offset);
    boolean existePorTipo(String tipo);
    TipoUsuario findById(String Id);
    void alterar(AlterarTipoUsuarioUseCase.AlterarTipoUsuarioCommand alterarTipoUsuarioCommand);
    Integer delete(String id);
}
