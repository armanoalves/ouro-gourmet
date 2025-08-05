package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.entities.Cardapio;
import br.com.ourogourmet.domain.exceptions.CardapioNaoEncontradoException;
import br.com.ourogourmet.domain.gateway.CardapioGateway;
import br.com.ourogourmet.domain.usecases.AlterarCardapioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlterarCardapioService implements AlterarCardapioUseCase {

    private final CardapioGateway cardapioRepository;

    @Override
    public void alterar(AlterarCardapioComand cardapioComand) {
        Cardapio cardapio = cardapioRepository.getCardapioById(cardapioComand.id());
        if (cardapio == null) {
            throw new CardapioNaoEncontradoException();
        }

        cardapio.setNome(cardapioComand.nome());
        cardapio.setDescricao(cardapioComand.descricao());
        cardapio.setPreco(cardapioComand.preco());
        cardapio.setConsumoLocal(cardapioComand.cosumoLocal());
        cardapio.setFoto(cardapioComand.foto());
        this.cardapioRepository.atualizarCardapio(cardapio);
    }

}
