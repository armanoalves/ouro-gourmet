package br.com.ourogourmet.cardapio.services;

import br.com.ourogourmet.cardapio.entities.Cardapio;
import br.com.ourogourmet.cardapio.repositories.CardapioRepository;
import br.com.ourogourmet.cardapio.usecase.AlterarCardapioUseCase;
import br.com.ourogourmet.usuario.exceptions.UsuarioDuplicadoException;
import br.com.ourogourmet.usuario.exceptions.UsuarioNaoEncontradoException;
import br.com.ourogourmet.usuario.exceptions.UsuarioValidacaoException;
import org.springframework.stereotype.Service;

@Service
public class AlterarCardapioService implements AlterarCardapioUseCase {

    private final CardapioRepository cardapioRepository;

    public AlterarCardapioService(CardapioRepository cardapioRepository) {
        this.cardapioRepository = cardapioRepository;
    }

    public void update(AlterarCardapioDTO cardapioDTO, String id) {
        var cardapioExistente = cardapioRepository.findById(id);
        Cardapio cardapioAlterado = new Cardapio(cardapioDTO);
        var update = this.cardapioRepository.update(cardapioAlterado, id);
        if (update == 0) {
            throw new UsuarioNaoEncontradoException();
        }
    }


}
