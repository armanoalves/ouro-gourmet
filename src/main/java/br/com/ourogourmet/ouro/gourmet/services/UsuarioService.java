package br.com.ourogourmet.ouro.gourmet.services;

import br.com.ourogourmet.ouro.gourmet.dto.AlterarUsuarioDTO;
import br.com.ourogourmet.ouro.gourmet.dto.CriarUsuarioDTO;
import br.com.ourogourmet.ouro.gourmet.entities.Usuario;
import br.com.ourogourmet.ouro.gourmet.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAll(int page, int size){
        int offset = (page-1) * size;
        return this.usuarioRepository.findAll(size,offset);
    }

    public Optional<Usuario> findById(String id){
        return this.usuarioRepository.findById(id);
    }

    public void save(CriarUsuarioDTO usuario){

        var incluirUsuario = new Usuario(usuario.email(), usuario.login(), usuario.ativo());
        var save = this.usuarioRepository.save(incluirUsuario);
        Assert.state(save == 1, "Erro ao salvar usuario: " + usuario.email());
    }

    public void update(AlterarUsuarioDTO usuario, String id){

        var alterarUsuario = new Usuario(usuario.email(), usuario.login(), usuario.ativo());
        var update = this.usuarioRepository.update(alterarUsuario,id);

        if (update == 0){
            throw new RuntimeException("Veículo não encontrado");
        }
    }

    public void delete(String id){
        var delete = this.usuarioRepository.delete(id);

        if (delete == 0){
            throw new RuntimeException("Veículo não encontrado");
        }
    }
}
