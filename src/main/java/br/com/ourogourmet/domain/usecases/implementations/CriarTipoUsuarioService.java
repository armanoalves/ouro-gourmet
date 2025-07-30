package br.com.ourogourmet.domain.usecases.implementations;

import br.com.ourogourmet.domain.entities.TipoUsuario;
import br.com.ourogourmet.domain.exceptions.TipoUsuarioCamposInvalidosException;
import br.com.ourogourmet.domain.gateway.TipoUsuarioGateway;
import br.com.ourogourmet.domain.usecases.CriarTipoUsuarioUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class CriarTipoUsuarioService implements CriarTipoUsuarioUseCase {

    private final TipoUsuarioGateway tipoUsuarioRepository;

    @Override
    public Long execute(CriarTipoUsuarioCommand tipocommand) {
        Set<String> violations = new HashSet<>();
        final String tipo = getTipo(tipocommand);

        if (tipo.isBlank()) {
            violations.add("O tipo de usuário é obrigatório.");
        }
        if (tipo.length() < 3) {
            violations.add("O tipo de usuário deve ter no mínimo 3 caracteres.");
        }
        if (tipo.length() > 50) {
            violations.add("O tipo de usuário deve ter no máximo 50 caracteres.");
        }
        if (tipoUsuarioRepository.existePorTipo(tipo)) {
            violations.add("Já existe um tipo de usuário com este nome.");
        }

        // ✅ Se houver erros, lança exceção personalizada
        if (!violations.isEmpty()) {
            throw new TipoUsuarioCamposInvalidosException(violations);
        }

        final TipoUsuario tipoUsuario = TipoUsuario.create(tipo);
        return tipoUsuarioRepository.incluir(tipoUsuario);
    }

    private static String getTipo(CriarTipoUsuarioCommand tipocommand) {
        return tipocommand.tipoUsuario() == null ? "" : tipocommand.tipoUsuario().trim().toUpperCase();
    }
}
