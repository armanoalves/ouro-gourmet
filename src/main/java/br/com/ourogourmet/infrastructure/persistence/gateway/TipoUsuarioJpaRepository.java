package br.com.ourogourmet.infrastructure.persistence.gateway;

import br.com.ourogourmet.infrastructure.persistence.TipoUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoUsuarioJpaRepository extends JpaRepository<TipoUsuarioEntity, Long> {
}
