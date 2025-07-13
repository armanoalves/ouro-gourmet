package br.com.ourogourmet.application.interfaces;


import br.com.ourogourmet.core.dto.AlterarUsuarioDTO;

public interface AlterarUsuarioUseCase{
    void update(AlterarUsuarioDTO usuario, String id);
}


