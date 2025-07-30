package br.com.ourogourmet.infrastructure.repository;

import br.com.ourogourmet.infrastructure.model.TipoUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoUsuarioJpaRepository extends JpaRepository<TipoUsuarioEntity, Long> {
    boolean existsByTipoIgnoreCase(String tipo);
}
