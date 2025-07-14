package br.com.ourogourmet.cardapio.services;

import br.com.ourogourmet.cardapio.entities.Cardapio;
import br.com.ourogourmet.cardapio.exceptions.CardapioCriacaoFalhaException;
import br.com.ourogourmet.cardapio.repositories.CardapioRepository;
import br.com.ourogourmet.cardapio.usecase.CriarCardapioUseCase;
import org.springframework.stereotype.Service;

@Service
public class CriarCardapioService implements CriarCardapioUseCase {

    private final CardapioRepository cardapioRepository;

    public CriarCardapioService(CardapioRepository cardapioRepository) {
        this.cardapioRepository = cardapioRepository;
    }

    public void save(CriarCardapioUseCase.CriarCardapioDTO cardapioDTO) {
        var incluirCardapio = new Cardapio(cardapioDTO);
        var save = this.cardapioRepository.save(incluirCardapio);
        if (save != 1) {
            throw new CardapioCriacaoFalhaException("Erro ao salvar Cardapio");
        }
    }
}
