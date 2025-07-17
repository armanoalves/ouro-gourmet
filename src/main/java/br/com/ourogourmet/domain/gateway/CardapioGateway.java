package br.com.ourogourmet.domain.gateway;

import br.com.ourogourmet.domain.entities.Cardapio;

import java.util.List;

public interface CardapioGateway {

    Cardapio buscarPorId(String id);
    List<Cardapio> buscarTodos(int size, int offset);
    Integer salvar(Cardapio cardapio);
    Integer atualizar(Cardapio cardapio, String id);
    Integer deletar(String id);
}
