package br.com.ourogourmet.application.usecases.implementations;

import br.com.ourogourmet.application.interfaces.CardapioGateway;
import br.com.ourogourmet.application.usecases.GetAllCardapioUseCase;
import br.com.ourogourmet.core.entities.Cardapio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllCardapioService implements GetAllCardapioUseCase {

    private final CardapioGateway cardapioRepository;

    public GetAllCardapioService(CardapioGateway cardapioRepository){
        this.cardapioRepository = cardapioRepository;
    }

    @Override
    public List<Cardapio> findAll(int page, int size){
        int offset = (page-1) * size;

        return this.cardapioRepository.findAll(size,offset);
    }
}
