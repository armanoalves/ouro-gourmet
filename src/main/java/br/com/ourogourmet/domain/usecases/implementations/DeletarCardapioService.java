package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.gateway.CardapioGateway;
import br.com.ourogourmet.domain.usecases.DeletarCardapioUseCase;
import br.com.ourogourmet.domain.exceptions.CardapioNaoDeletadoException;
import br.com.ourogourmet.domain.exceptions.CardapioNaoEncontradoException;
import org.springframework.stereotype.Service;

@Service
public class DeletarCardapioService implements DeletarCardapioUseCase {

    private final CardapioGateway cardapioRepository;

    public DeletarCardapioService(CardapioGateway cardapioRepository) {
        this.cardapioRepository = cardapioRepository;
    }

    @Override
    public void delete(String id) {
        var delete = this.cardapioRepository.deletar(id);

        if (delete == 0) {
            throw new CardapioNaoEncontradoException();
        }

        if (delete < 0) {
            throw new CardapioNaoDeletadoException(id);
        }
    }
}
