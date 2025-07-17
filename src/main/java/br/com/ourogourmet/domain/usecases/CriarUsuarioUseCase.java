package br.com.ourogourmet.domain.usecases;

public interface CriarUsuarioUseCase {
        void save(CriarUsuarioCommand usuario);

        record CriarUsuarioCommand(
                String nome,
                String endereco,
                String senha,
                String email,
                String login,
                Boolean ativo) {
        }
}
