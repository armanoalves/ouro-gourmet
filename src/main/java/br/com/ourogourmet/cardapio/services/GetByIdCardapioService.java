package br.com.ourogourmet.cardapio.services;

import br.com.ourogourmet.cardapio.entities.Cardapio;
import br.com.ourogourmet.cardapio.repositories.CardapioRepository;
import br.com.ourogourmet.cardapio.usecase.GetByIdCardapioUseCase;
import br.com.ourogourmet.usuario.entities.Usuario;
import br.com.ourogourmet.usuario.repositories.UsuarioRepository;
import br.com.ourogourmet.usuario.usecase.GetByIdUsuarioUseCase;
import org.springframework.stereotype.Service;

@Service
public class GetByIdCardapioService implements GetByIdCardapioUseCase {

    private final CardapioRepository cardapioRepository;

    public GetByIdCardapioService(CardapioRepository cardapioRepository){
        this.cardapioRepository = cardapioRepository;
    }

    @Override
    public Cardapio findById(String id){
        return this.cardapioRepository.findById(id);
    }
}
