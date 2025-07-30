package br.com.ourogourmet.domain.usecases;

public interface CriarTipoUsuarioUseCase {
    Long execute(CriarTipoUsuarioCommand usuario);

    record CriarTipoUsuarioCommand(
            String tipoUsuario){
    }
}
