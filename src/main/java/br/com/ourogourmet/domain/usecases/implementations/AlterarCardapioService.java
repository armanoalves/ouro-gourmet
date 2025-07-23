package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.entities.Cardapio;
import br.com.ourogourmet.domain.gateway.CardapioGateway;
import br.com.ourogourmet.domain.usecases.AlterarCardapioUseCase;
import org.springframework.stereotype.Service;

@Service
public class AlterarCardapioService implements AlterarCardapioUseCase {

    private final CardapioGateway cardapioRepository;

    public AlterarCardapioService(CardapioGateway cardapioRepository) {
        this.cardapioRepository = cardapioRepository;
    }

    public void alterar(AlterarCardapioComand cardapioDTO, Long id) {
        var cardapioExistente = cardapioRepository.getCardapioById(id);
        Cardapio cardapioAlterado = cardapioExistente.alterar(cardapioDTO);
        var update = this.cardapioRepository.atualizarCardapio(cardapioAlterado, id);
    }

}
