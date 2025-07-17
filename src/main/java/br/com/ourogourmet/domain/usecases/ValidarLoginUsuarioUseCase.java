package br.com.ourogourmet.domain.usecases;

public interface ValidarLoginUsuarioUseCase {

    void validar(ValidarLoginUsuarioCommand cmd);

    record ValidarLoginUsuarioCommand(
            String login,
            String senha)
    {}

}
