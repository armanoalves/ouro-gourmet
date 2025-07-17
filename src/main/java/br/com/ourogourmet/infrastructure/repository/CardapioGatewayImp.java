package br.com.ourogourmet.infrastructure.repository;

import br.com.ourogourmet.domain.entities.Cardapio;
import br.com.ourogourmet.domain.exceptions.CardapioNaoEncontradoException;
import br.com.ourogourmet.domain.gateway.CardapioGateway;
import br.com.ourogourmet.infrastructure.model.CardapioEntity;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("!test")
public class CardapioGatewayImp implements CardapioGateway {

    private final CardapioJpaRepository cardapioJpaRepository;

    public CardapioGatewayImp(CardapioJpaRepository cardapioJpaRepository) {
        this.cardapioJpaRepository = cardapioJpaRepository;
    }

    @Override
    public Cardapio buscarPorId(String id) {
        CardapioEntity cardapioEntity = cardapioJpaRepository.findById(id)
                .orElseThrow(() -> new CardapioNaoEncontradoException());
        return Cardapio.create(cardapioEntity.getNome(),
                cardapioEntity.getDescricao(),
                cardapioEntity.getPreco(),
                cardapioEntity.getConsumoLocal(),
                cardapioEntity.getFoto());
    }

    @Override
    public List<Cardapio> buscarTodos(int size, int offset) {
        return List.of();
    }

    @Override
    public Integer salvar(Cardapio cardapio) {
        return 0;
    }

    @Override
    public Integer atualizar(Cardapio cardapio, String id) {
        return 0;
    }

    @Override
    public Integer deletar(String id) {
        return 0;
    }
}
