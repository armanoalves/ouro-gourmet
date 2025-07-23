package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.gateway.CardapioGateway;
import br.com.ourogourmet.domain.usecases.CriarCardapioUseCase;
import br.com.ourogourmet.domain.entities.Cardapio;
import br.com.ourogourmet.domain.exceptions.CardapioCriacaoFalhaException;
import org.springframework.stereotype.Service;

@Service
public class CriarCardapioService implements CriarCardapioUseCase {

    private final CardapioGateway cardapioRepository;

    public CriarCardapioService(CardapioGateway cardapioRepository) {

        this.cardapioRepository = cardapioRepository;
    }

    public void criar(CriarCardapioCommand cardapioDTO) {

        var incluirCardapio = Cardapio.incluir(cardapioDTO);

        var save = this.cardapioRepository.criarCardapio(incluirCardapio);

        if (save == null) {
            throw new CardapioCriacaoFalhaException("Erro ao salvar Cardapio");
        }
    }
}
