package br.com.ourogourmet.application.usecases;


import br.com.ourogourmet.core.dto.AlterarUsuarioDTO;

public interface AlterarUsuarioUseCase{
    void update(AlterarUsuarioDTO usuario, String id);
}


