package br.com.ourogourmet.application.presenter;

import br.com.ourogourmet.domain.entities.Restaurante;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantePresenter implements Presenter<Restaurante,RestauranteResponse> {

    @Override
    public RestauranteResponse presenter(Restaurante response) {

        UsuarioResponse usuarioResponse = Optional.ofNullable(response.getUsuario())
                .map(usuario -> new UsuarioResponse(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getLogin(),
                        usuario.getAtivo(),
                        usuario.getEndereco(),
                        usuario.getTipoUsuarioId()))
                .orElse(null);

        return new RestauranteResponse(response.getId(),
                response.getNome(),
                response.getTipoCozinha(),
                response.getHorarioFuncionamentoDe(),
                response.getHorarioFuncionamentoAte(),
                usuarioResponse );
    }
}
