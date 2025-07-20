package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.gateway.CardapioGateway;
import br.com.ourogourmet.domain.usecases.AlterarCardapioUseCase;
import br.com.ourogourmet.domain.entities.Cardapio;
import br.com.ourogourmet.domain.exceptions.CardapioNaoEncontradoException;
import org.springframework.stereotype.Service;

@Service
public class AlterarCardapioService implements AlterarCardapioUseCase {

    private final CardapioGateway cardapioRepository;

    public AlterarCardapioService(CardapioGateway cardapioRepository) {
        this.cardapioRepository = cardapioRepository;
    }

    public void update(AlterarCardapioComand cardapioDTO, String id) {
        var cardapioExistente = cardapioRepository.buscarPorId(id);

        if (cardapioExistente == null) {
            throw new CardapioNaoEncontradoException();
        }

        Cardapio cardapioAlterado = cardapioExistente.alterar(cardapioDTO);

        var update = this.cardapioRepository.atualizar(cardapioAlterado, id);

    }

}
