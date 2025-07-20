package br.com.ourogourmet.infrastructure.repository;

import br.com.ourogourmet.infrastructure.model.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long> {

    Optional<RestauranteEntity> findByNome(String nome);

}
