package br.com.ourogourmet.infrastructure.presenter;

import br.com.ourogourmet.domain.entities.Restaurante;

import static java.util.Objects.nonNull;

public class RestaurantePresenter implements Presenter<Restaurante,RestauranteResponse> {

    @Override
    public RestauranteResponse presenter(Restaurante response) {

        var usuario = nonNull(response.getUsuario())
                ?  new UsuarioResponse(response.getUsuario().getId(),
                response.getUsuario().getNome(),
                response.getUsuario().getEmail(),
                response.getUsuario().getLogin(),
                response.getUsuario().getAtivo(),
                response.getUsuario().getEndereco())
                : null;

        return new RestauranteResponse(response.getId(),
                response.getNome(),
                response.getTipoCozinha(),
                response.getHorarioFuncionamentoDe(),
                response.getHorarioFuncionamentoAte(),
                usuario );
    }
}
