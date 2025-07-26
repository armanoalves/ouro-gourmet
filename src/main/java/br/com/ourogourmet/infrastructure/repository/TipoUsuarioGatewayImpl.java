package br.com.ourogourmet.infrastructure.repository;

import br.com.ourogourmet.domain.entities.TipoUsuario;
import br.com.ourogourmet.domain.exceptions.TipoUsuarioNaoEncontradoException;
import br.com.ourogourmet.domain.gateway.TipoUsuarioGateway;
import br.com.ourogourmet.domain.usecases.AlterarTipoUsuarioUseCase;
import br.com.ourogourmet.infrastructure.model.TipoUsuarioEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TipoUsuarioGatewayImpl implements TipoUsuarioGateway {

    private final TipoUsuarioJpaRepository tipoUsuarioJpaRepository;

    public TipoUsuarioGatewayImpl(TipoUsuarioJpaRepository tipoUsuarioJpaRepository) {
        this.tipoUsuarioJpaRepository = tipoUsuarioJpaRepository;
    }

    @Override
    public Long incluir(TipoUsuario tipoUsuario) {
        TipoUsuarioEntity entity = new TipoUsuarioEntity(tipoUsuario);
        return tipoUsuarioJpaRepository.save(entity).getId();
    }

    @Override
    public List<TipoUsuario> findAll(int size, int offset) {
        return tipoUsuarioJpaRepository.findAll()
                .stream()
                .map(TipoUsuarioEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public TipoUsuario findById(String id) {
        return tipoUsuarioJpaRepository.findById(Long.parseLong(id))
                .map(TipoUsuarioEntity::toDomain)
                .orElseThrow(() -> new TipoUsuarioNaoEncontradoException(id));
    }

    @Override
    public void alterar(AlterarTipoUsuarioUseCase.AlterarTipoUsuarioCommand command) {
        TipoUsuarioEntity entity = tipoUsuarioJpaRepository.findById(command.id())
                .orElseThrow(() -> new TipoUsuarioNaoEncontradoException(command.id().toString()));

        entity = new TipoUsuarioEntity(entity.getId(), command.tipo());
        tipoUsuarioJpaRepository.save(entity);
    }

    @Override
    public Integer delete(String id) {
        Long longId = Long.parseLong(id);
        if (!tipoUsuarioJpaRepository.existsById(longId)) {
            throw new TipoUsuarioNaoEncontradoException(id);
        }
        tipoUsuarioJpaRepository.deleteById(longId);
        return 1;
    }
}