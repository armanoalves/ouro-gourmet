package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.gateway.CardapioGateway;
import br.com.ourogourmet.domain.usecases.GetAllCardapioUseCase;
import br.com.ourogourmet.domain.entities.Cardapio;
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
        return this.cardapioRepository.getAllCardapio(size,offset);
    }
}
