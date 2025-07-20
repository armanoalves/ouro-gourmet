package br.com.ourogourmet.infrastructure.adapter.repository;

import br.com.ourogourmet.domain.entities.Restaurante;
import br.com.ourogourmet.domain.gateway.RestauranteGateway;
import br.com.ourogourmet.infrastructure.model.RestauranteEntity;
import br.com.ourogourmet.infrastructure.model.UsuarioEntity;
import br.com.ourogourmet.infrastructure.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public class RestauranteEntityRepository implements RestauranteGateway {

    private final RestauranteRepository restauranteRepository;

    @Override
    public Long incluir(Restaurante restaurante) {
        var newRestaurante = this.restauranteRepository.save(new RestauranteEntity(restaurante));
        return newRestaurante.getId();
    }

    @Override
    public Optional<Restaurante> buscarPorId(Long id) {

        var restauranteEntity = this.restauranteRepository.findById(id);

        return restauranteEntity.map(RestauranteEntity::toDomain);
    }

    @Override
    public Optional<Restaurante> buscarPorNome(String nome) {

        var restauranteEntity = this.restauranteRepository.findByNome(nome);

        return restauranteEntity.map(RestauranteEntity::toDomain);

    }

    @Override
    public List<Restaurante> buscarTodos(int page, int pageSize) {

        var restaurantes = this.restauranteRepository.findAll(PageRequest.of(page-1, pageSize)).getContent();

        return restaurantes.stream()
                .map(RestauranteEntity::toDomain)
                .toList();
    }

    @Override
    public void alterar(Restaurante restaurante) {

        this.restauranteRepository.findById(restaurante.getId()).ifPresent(present->{
            present.setNome(restaurante.getNome());
            present.setTipoCozinha(restaurante.getTipoCozinha());
            present.setHorarioFuncionamentoDe(restaurante.getHorarioFuncionamentoDe());
            present.setHorarioFuncionamentoAte(restaurante.getHorarioFuncionamentoAte());
            present.setUsuario(nonNull(restaurante.getUsuario()) ? new UsuarioEntity(restaurante.getUsuario()) : null);
            this.restauranteRepository.save(present);
        });
    }

    @Override
    public void alterarUsuario(Restaurante restaurante) {

        this.restauranteRepository.findById(restaurante.getId()).ifPresent(present->{
            present.setUsuario(nonNull(restaurante.getUsuario()) ? new UsuarioEntity(restaurante.getUsuario()) : null);
            this.restauranteRepository.save(present);
        });

    }

    @Override
    public void deletar(Restaurante restaurante) {

        var restauranteEntity = this.restauranteRepository.findById(restaurante.getId());

        restauranteEntity.ifPresent(this.restauranteRepository::delete);
    }

}
