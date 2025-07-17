package br.com.ourogourmet.domain.gateway;

import br.com.ourogourmet.domain.entities.Cardapio;

import java.util.List;

public interface CardapioGateway {

    Cardapio findById(String id);
    List<Cardapio> findAll(int size, int offset);
    Integer save(Cardapio cardapio);
    Integer update(Cardapio cardapio, String id);
    Integer delete(String id);
}
