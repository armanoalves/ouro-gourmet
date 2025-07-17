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

    public void save(CriarCardapioCommand cardapioDTO) {

        var incluirCardapio = Cardapio.incluir(cardapioDTO);

        var save = this.cardapioRepository.save(incluirCardapio);

        if (save != 1) {
            throw new CardapioCriacaoFalhaException("Erro ao salvar Cardapio");
        }
    }
}
