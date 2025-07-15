package br.com.ourogourmet.application.usecases.implementations;

import br.com.ourogourmet.application.interfaces.CardapioGateway;
import br.com.ourogourmet.application.usecases.AlterarCardapioUseCase;
import br.com.ourogourmet.core.entities.Cardapio;
import br.com.ourogourmet.core.exceptions.CardapioNaoEncontradoException;
import org.springframework.stereotype.Service;

@Service
public class AlterarCardapioService implements AlterarCardapioUseCase {

    private final CardapioGateway cardapioRepository;

    public AlterarCardapioService(CardapioGateway cardapioRepository) {
        this.cardapioRepository = cardapioRepository;
    }

    public void update(AlterarCardapioComand cardapioDTO, String id) {
        var cardapioExistente = cardapioRepository.findById(id);

        if (cardapioExistente == null) {
            throw new CardapioNaoEncontradoException();
        }

        Cardapio cardapioAlterado = cardapioExistente.alterar(cardapioDTO);

        var update = this.cardapioRepository.update(cardapioAlterado, id);

    }

}
