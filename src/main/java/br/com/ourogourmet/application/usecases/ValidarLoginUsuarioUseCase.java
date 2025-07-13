package br.com.ourogourmet.application.usecases;

public interface ValidarLoginUsuarioUseCase {

    void validar(ValidarLoginUsuarioCommand cmd);

    record ValidarLoginUsuarioCommand(
            String login,
            String senha)
    {}

}
