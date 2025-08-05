package br.com.ourogourmet.application.controller;

import br.com.ourogourmet.application.controller.dtos.AlterarTipoUsuarioDTO;
import br.com.ourogourmet.application.controller.dtos.CriarTipoUsuarioDTO;
import br.com.ourogourmet.domain.entities.TipoUsuario;
import br.com.ourogourmet.domain.exceptions.TipoUsuarioCamposInvalidosException;
import br.com.ourogourmet.domain.usecases.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromPath;

@Data
@RestController
@RequestMapping("/tipoUsuario")
@Tag(name="Tipo Usuário", description = "Cadastro de tipo de usuario")
public class TipoUsuarioController {

    private final CriarTipoUsuarioUseCase criarTipoUsuario;
    private final AlterarTipoUsuarioUseCase alterarTipoUsuario;
    private final DeletarTipoUsuarioUseCase deletarTipoUsuario;
    private final GetAllTipoUsuarioUseCase getAllTipoUsuario;
    private final GetByIdTipoUsuarioUseCase getByIdTipoUsuario;
    private final Validator validator;

    public static final String PATH = "/tipoUsuario";

    public TipoUsuarioController(CriarTipoUsuarioUseCase criarTipoUsuario,
                                 AlterarTipoUsuarioUseCase alterarTipoUsuario,
                                 DeletarTipoUsuarioUseCase deletarTipoUsuario,
                                 GetAllTipoUsuarioUseCase getAllTipoUsuario,
                                 GetByIdTipoUsuarioUseCase getByIdTipoUsuario,
                                 Validator validator){
        this.criarTipoUsuario = criarTipoUsuario;
        this.alterarTipoUsuario = alterarTipoUsuario;
        this.deletarTipoUsuario = deletarTipoUsuario;
        this.getAllTipoUsuario = getAllTipoUsuario;
        this.getByIdTipoUsuario = getByIdTipoUsuario;
        this.validator = validator;
    }

    @GetMapping
    public ResponseEntity<List<TipoUsuario>> findAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size){

        var tipoUsuario = this.getAllTipoUsuario.findAll(page, size);
        return ResponseEntity.ok (tipoUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuario> findById(
            @PathVariable("id") String id){

        var tipoUsuario = this.getByIdTipoUsuario.findById(id);
        return ResponseEntity.ok(tipoUsuario);
    }

    @PostMapping
    public ResponseEntity<Void> criarTipoUsuario(
            @RequestBody CriarTipoUsuarioDTO dto) {

        var cmd = new CriarTipoUsuarioUseCase.CriarTipoUsuarioCommand(dto.tipoUsuario());
        var id = criarTipoUsuario.execute(cmd);

        ResponseEntity.created(fromPath(TipoUsuarioController.PATH + "/{id}").build(id)).build();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> alterarTipoUsuario(
            @PathVariable("id") Long id,
            @RequestBody AlterarTipoUsuarioDTO dto){

        var erros = this.validator.validateObject(dto)
                .getAllErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toSet());

        if (!erros.isEmpty())
            throw new TipoUsuarioCamposInvalidosException(erros);

        var cmd = new AlterarTipoUsuarioUseCase.AlterarTipoUsuarioCommand(id,
                dto.tipo());

        this.alterarTipoUsuario.execute(cmd);

        var status = HttpStatus.NO_CONTENT;

        return ResponseEntity.status(status.value()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") String id){
        this.deletarTipoUsuario.delete(id);
        return ResponseEntity.ok().build();
    }
}
