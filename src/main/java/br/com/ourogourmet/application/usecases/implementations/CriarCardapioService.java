package br.com.ourogourmet.application.usecases.implementations;

import br.com.ourogourmet.application.interfaces.CardapioGateway;
import br.com.ourogourmet.application.usecases.CriarCardapioUseCase;
import br.com.ourogourmet.core.entities.Cardapio;
import br.com.ourogourmet.core.exceptions.CardapioCriacaoFalhaException;
import br.com.ourogourmet.application.usecases.CriarCardapioUseCase.CriarCardapioCommand;
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
