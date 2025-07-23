package br.com.ourogourmet.infrastructure.repository;

import br.com.ourogourmet.domain.entities.Cardapio;
import br.com.ourogourmet.domain.exceptions.CardapioNaoEncontradoException;
import br.com.ourogourmet.domain.gateway.CardapioGateway;
import br.com.ourogourmet.infrastructure.model.CardapioEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardapioGatewayImp implements CardapioGateway {

    private final CardapioJpaRepository cardapioJpaRepository;

    public CardapioGatewayImp(CardapioJpaRepository cardapioJpaRepository) {
        this.cardapioJpaRepository = cardapioJpaRepository;
    }

    @Override
    public Cardapio getCardapioById(Long id) {
        CardapioEntity cardapioEntity = cardapioJpaRepository.findById(id)
                .orElseThrow(CardapioNaoEncontradoException::new);
        return cardapioEntity.toDomain();
    }

    @Override
    public List<Cardapio> getAllCardapio(int size, int offset) {
        List<CardapioEntity> cardapioEntities = cardapioJpaRepository.findAll();
        return cardapioEntities.stream()
                .map(CardapioEntity::toDomain)
                .toList();
    }

    @Override
    public Cardapio criarCardapio(Cardapio cardapio) {
        var cardapioSalvo = cardapioJpaRepository.save(new CardapioEntity(cardapio));
        return cardapioSalvo.toDomain();
    }

    @Override
    public Cardapio atualizarCardapio(Cardapio cardapio, Long id) {
        CardapioEntity cardapioEntity = cardapioJpaRepository.findById(id)
                .orElseThrow(CardapioNaoEncontradoException::new);
        cardapioEntity.setNome(cardapio.getNome());
        cardapioEntity.setDescricao(cardapio.getDescricao());
        cardapioEntity.setConsumoLocal(cardapio.getConsumoLocal());
        cardapioEntity.setPreco(cardapio.getPreco());
        cardapioEntity.setFoto(cardapio.getFoto());
        return cardapioEntity.toDomain();
    }

    @Override
    public void deletarCardapio(Long id) {
        if (!cardapioJpaRepository.existsById(id)) {
            throw new CardapioNaoEncontradoException();
        }
        cardapioJpaRepository.deleteById(id);
    }
}
