package br.com.ourogourmet.infrastructure.repository;

import br.com.ourogourmet.infrastructure.model.CardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardapioGatewayImpl2 extends JpaRepository<CardapioEntity,String> {



}
