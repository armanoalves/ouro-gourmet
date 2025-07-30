package br.com.ourogourmet.domain.usecases;


public interface AlterarUsuarioUseCase{
    void update(AlterarUsuarioCommand usuario, String id);

    record AlterarUsuarioCommand(
            String nome,
            String endereco,
            String email,
            String login,
            Boolean ativo,
            Long tipoUsuarioId) {
    }
}


