package br.com.ourogourmet.application.usecases;


import br.com.ourogourmet.infrastructure.web.controller.dtos.AlterarUsuarioDTO;

public interface AlterarUsuarioUseCase{
    void update(AlterarUsuarioDTO usuario, String id);
}


