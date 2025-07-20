package br.com.ourogourmet.domain.gateway;

import br.com.ourogourmet.domain.entities.Restaurante;

import java.util.List;
import java.util.Optional;

public interface RestauranteGateway {

    Long incluir(Restaurante restaurante);
    Optional<Restaurante> buscarPorId(Long id);
    Optional<Restaurante> buscarPorNome(String nome);
    List<Restaurante> buscarTodos(int page, int pageSize);
    void alterar(Restaurante restaurante);
    void deletar(Restaurante restaurante);
}
