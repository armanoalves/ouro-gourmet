package br.com.ourogourmet.infrastructure.repository;

import br.com.ourogourmet.infrastructure.model.CardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardapioJpaRepository extends JpaRepository<CardapioEntity, String>{

}
