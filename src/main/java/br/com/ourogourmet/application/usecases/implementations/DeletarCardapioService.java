package br.com.ourogourmet.application.usecases.implementations;

import br.com.ourogourmet.application.interfaces.CardapioGateway;
import br.com.ourogourmet.application.usecases.DeletarCardapioUseCase;
import br.com.ourogourmet.core.exceptions.CardapioNaoDeletadoException;
import br.com.ourogourmet.core.exceptions.CardapioNaoEncontradoException;
import org.springframework.stereotype.Service;

@Service
public class DeletarCardapioService implements DeletarCardapioUseCase {

    private final CardapioGateway cardapioRepository;

    public DeletarCardapioService(CardapioGateway cardapioRepository) {
        this.cardapioRepository = cardapioRepository;
    }

    @Override
    public void delete(String id) {
        var delete = this.cardapioRepository.delete(id);

        if (delete == 0) {
            throw new CardapioNaoEncontradoException();
        }

        if (delete < 0) {
            throw new CardapioNaoDeletadoException(id);
        }
    }
}
