package br.com.ourogourmet.infrastructure.persistence.gateway;

import br.com.ourogourmet.infrastructure.persistence.CardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardapioGatewayImpl2 extends JpaRepository<CardapioEntity,String> {



}
