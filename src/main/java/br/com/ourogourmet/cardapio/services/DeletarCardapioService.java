package br.com.ourogourmet.cardapio.services;

import br.com.ourogourmet.cardapio.repositories.CardapioRepository;
import br.com.ourogourmet.cardapio.usecase.DeletarCardapioUseCase;
import br.com.ourogourmet.usuario.exceptions.UsuarioNaoDeletadoException;
import br.com.ourogourmet.usuario.exceptions.UsuarioNaoEncontradoException;
import br.com.ourogourmet.usuario.repositories.UsuarioRepository;
import br.com.ourogourmet.usuario.usecase.DeletarUsuarioUseCase;
import org.springframework.stereotype.Service;

@Service
public class DeletarCardapioService implements DeletarCardapioUseCase {

    private final CardapioRepository cardapioRepository;

    public DeletarCardapioService(CardapioRepository cardapioRepository) {
        this.cardapioRepository = cardapioRepository;
    }

    @Override
    public void delete(String id) {
        var delete = this.cardapioRepository.delete(id);

        if (delete == 0) {
            throw new UsuarioNaoEncontradoException();
        }

        if (delete < 0) {
            throw new UsuarioNaoDeletadoException(id);
        }
    }
}
