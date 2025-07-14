package br.com.ourogourmet.infrastructure.web.controller;

import br.com.ourogourmet.application.usecases.CriarTipoUsuarioUseCase;
import br.com.ourogourmet.infrastructure.web.controller.dtos.CriarTipoUsuarioDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.ourogourmet.application.usecases.CriarTipoUsuarioUseCase.CriarTipoUsuarioCommand;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromPath;

@RestController
@RequestMapping(path= TipoUsuarioController.PATH)
@Tag(name="Tipo_Usuario", description = "Cadastro tipo de usuario")
@AllArgsConstructor
public class TipoUsuarioController {

    private final CriarTipoUsuarioUseCase criarTipoUsuarioUseCase;
    public static final String PATH = "/tipo_usuario";

    @PostMapping
    public ResponseEntity<Void> criarTipoUsuario(
            @RequestBody CriarTipoUsuarioDTO dto) {

        var cmd = new CriarTipoUsuarioCommand(dto.tipoUsuarioEnum());
        var id = criarTipoUsuarioUseCase.execute(cmd);

        ResponseEntity.created(fromPath(TipoUsuarioController.PATH + "/{id}").build(id)).build();
        return ResponseEntity.ok().build();
    }
}
