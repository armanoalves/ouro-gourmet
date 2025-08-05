package br.com.ourogourmet.domain.usecases;

public interface AlterarTipoUsuarioUseCase {

    void execute(AlterarTipoUsuarioCommand tipoUsuarioCommand);

    record AlterarTipoUsuarioCommand(
            Long id,
            String tipo){
    }
}
