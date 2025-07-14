package br.com.ourogourmet.cardapio.services;

import br.com.ourogourmet.cardapio.entities.Cardapio;
import br.com.ourogourmet.cardapio.repositories.CardapioRepository;
import br.com.ourogourmet.cardapio.usecase.GetAllCardapioUseCase;
import br.com.ourogourmet.usuario.entities.Usuario;
import br.com.ourogourmet.usuario.repositories.UsuarioRepository;
import br.com.ourogourmet.usuario.usecase.GetAllUsuarioUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllCardapioService implements GetAllCardapioUseCase {

    private final CardapioRepository cardapioRepository;

    public GetAllCardapioService(CardapioRepository cardapioRepository){
        this.cardapioRepository = cardapioRepository;
    }

    @Override
    public List<Cardapio> findAll(int page, int size){
        int offset = (page-1) * size;

        return this.cardapioRepository.findAll(size,offset);
    }
}
