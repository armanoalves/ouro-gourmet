package br.com.ourogourmet.application.usecases.implementations;

import br.com.ourogourmet.application.interfaces.CardapioGateway;
import br.com.ourogourmet.application.usecases.GetByIdCardapioUseCase;
import br.com.ourogourmet.core.entities.Cardapio;
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
