package br.com.ourogourmet.cardapio.repositories;

import br.com.ourogourmet.cardapio.entities.Cardapio;
import br.com.ourogourmet.usuario.entities.Usuario;

import java.util.List;

public interface CardapioRepository {

    Cardapio findById(String id);

    List<Cardapio> findAll(int size, int offset);

    Integer save(Cardapio cardapio);

    Integer update(Cardapio cardapio, String id);

    Integer delete(String id);

}
