package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.gateway.CardapioGateway;
import br.com.ourogourmet.domain.usecases.DeletarCardapioUseCase;
import org.springframework.stereotype.Service;

@Service
public class DeletarCardapioService implements DeletarCardapioUseCase {

    private final CardapioGateway cardapioRepository;

    public DeletarCardapioService(CardapioGateway cardapioRepository) {
        this.cardapioRepository = cardapioRepository;
    }

    @Override
    public void deletar(Long id) {
        cardapioRepository.getCardapioById(id);
        cardapioRepository.deletarCardapio(id);
    }
}
