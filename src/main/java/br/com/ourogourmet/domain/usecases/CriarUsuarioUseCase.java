package br.com.ourogourmet.domain.usecases;

import br.com.ourogourmet.domain.entities.TipoUsuario;

public interface CriarUsuarioUseCase {
        void save(CriarUsuarioCommand usuario);

        record CriarUsuarioCommand(
                String nome,
                String endereco,
                String senha,
                String email,
                String login,
                Boolean ativo,
                Long tipoUsuarioId) {
        }
}
