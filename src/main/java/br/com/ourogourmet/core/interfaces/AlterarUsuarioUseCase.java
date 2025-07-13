package br.com.ourogourmet.core.interfaces;


import br.com.ourogourmet.core.dto.AlterarUsuarioDTO;

public interface AlterarUsuarioUseCase{
    void update(AlterarUsuarioDTO usuario, String id);
}


