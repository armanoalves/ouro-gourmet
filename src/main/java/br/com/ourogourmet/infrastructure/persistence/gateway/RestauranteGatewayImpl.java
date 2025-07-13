package br.com.ourogourmet.infrastructure.persistence.gateway;

import br.com.ourogourmet.application.interfaces.RestauranteGateway;
import br.com.ourogourmet.core.entities.Restaurante;
import br.com.ourogourmet.infrastructure.persistence.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RestauranteGatewayImpl extends JpaRepository<RestauranteEntity, UUID>,RestauranteGateway
{

    @Override
    default Long incluir(Restaurante restaurante) {
        var entity = new RestauranteEntity(restaurante);
        return this.save(entity).getId();
    }



}
