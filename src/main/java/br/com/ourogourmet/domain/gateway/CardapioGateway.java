package br.com.ourogourmet.domain.gateway;

import br.com.ourogourmet.domain.entities.Cardapio;

import java.util.List;

public interface CardapioGateway {

    Cardapio getCardapioById(Long id);
    List<Cardapio> getAllCardapio(int size, int offset);
    Cardapio criarCardapio(Cardapio cardapio);
    Cardapio atualizarCardapio(Cardapio cardapio, Long id);
    void deletarCardapio(Long id);
}
