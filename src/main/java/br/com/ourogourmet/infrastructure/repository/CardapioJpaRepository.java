package br.com.ourogourmet.infrastructure.repository;

import br.com.ourogourmet.infrastructure.model.CardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardapioJpaRepository extends JpaRepository<CardapioEntity, Long> {
}

