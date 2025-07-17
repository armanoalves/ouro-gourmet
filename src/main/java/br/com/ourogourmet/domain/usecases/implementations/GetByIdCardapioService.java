package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.gateway.CardapioGateway;
import br.com.ourogourmet.domain.usecases.GetByIdCardapioUseCase;
import br.com.ourogourmet.domain.entities.Cardapio;
import org.springframework.stereotype.Service;

@Service
public class GetByIdCardapioService implements GetByIdCardapioUseCase {

    private final CardapioGateway cardapioRepository;

    public GetByIdCardapioService(CardapioGateway cardapioRepository){
        this.cardapioRepository = cardapioRepository;
    }

    @Override
    public Cardapio findById(String id){
        return this.cardapioRepository.findById(id);
    }
}
