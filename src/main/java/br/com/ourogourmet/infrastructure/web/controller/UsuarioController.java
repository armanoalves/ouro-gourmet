package br.com.ourogourmet.infrastructure.web.controller;

import br.com.ourogourmet.application.usecases.*;
import br.com.ourogourmet.application.usecases.AlterarUsuarioUseCase.AlterarUsuarioCommand;
import br.com.ourogourmet.application.usecases.CriarUsuarioUseCase.CriarUsuarioCommand;
import br.com.ourogourmet.application.usecases.TrocarSenhaUsuarioUseCase.TrocarSenhaUsuarioCommand;
import br.com.ourogourmet.application.usecases.ValidarLoginUsuarioUseCase.ValidarLoginUsuarioCommand;
import br.com.ourogourmet.core.entities.Usuario;
import br.com.ourogourmet.core.exceptions.UsuarioCamposInvalidosException;
import br.com.ourogourmet.infrastructure.web.controller.dtos.AlterarUsuarioDTO;
import br.com.ourogourmet.infrastructure.web.controller.dtos.CriarUsuarioDTO;
import br.com.ourogourmet.infrastructure.web.controller.dtos.TrocarSenhaUsuarioDTO;
import br.com.ourogourmet.infrastructure.web.controller.dtos.ValidarLoginUsuarioDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@RestController
@RequestMapping("/usuarios")
@Tag(name="Usuários", description = "Cadastro de usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final CriarUsuarioUseCase criarUsuario;
    private final AlterarUsuarioUseCase alterarUsuario;
    private final DeletarUsuarioUseCase deletarUsuario;
    private final GetAllUsuarioUseCase getAllUsuarios;
    private final GetByIdUsuarioUseCase getByIdUsuario;
    private final TrocarSenhaUsuarioUseCase trocarSenhaUsuario;
    private final ValidarLoginUsuarioUseCase validarLoginUsuario;

    private final Validator validator;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAllUsuarios(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size){

        var usuarios = this.getAllUsuarios.findAll(page, size);

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findUsuarioById(
            @PathVariable("id") String id){

        var usuario = this.getByIdUsuario.findById(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Void> saveUsuario(
            @RequestBody CriarUsuarioDTO usuario){

        var erros = this.validator.validateObject(usuario)
                .getAllErrors()
                        .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toSet());

        if (!erros.isEmpty())
            throw new UsuarioCamposInvalidosException(erros);

        this.criarUsuario.save(new CriarUsuarioCommand(usuario.nome(),
                usuario.endereco(),
                usuario.senha(),
                usuario.email(),
                usuario.login(),
                usuario.ativo()) );
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/validar")
    public ResponseEntity<String> validar(
            @RequestBody ValidarLoginUsuarioDTO dto) {
        var erros = this.validator.validateObject(dto)
                .getAllErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toSet());

        if (!erros.isEmpty())
            throw new UsuarioCamposInvalidosException(erros);

        this.validarLoginUsuario.validar(new ValidarLoginUsuarioCommand(dto.login(),dto.senha()));

        return ResponseEntity.ok().body("Usuário validado!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUsuario(
            @PathVariable("id") String id,
            @RequestBody AlterarUsuarioDTO usuario){

        var erros = this.validator.validateObject(usuario)
                .getAllErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toSet());

        if (!erros.isEmpty())
            throw new UsuarioCamposInvalidosException(erros);

        this.alterarUsuario.update( new AlterarUsuarioCommand(usuario.nome(),
                        usuario.endereco(),
                        usuario.email(),
                        usuario.login(),
                        usuario.ativo())
                ,id);

        var status = HttpStatus.NO_CONTENT;

        return ResponseEntity.status(status.value()).build();
    }

    @PatchMapping("/{id}/senha")
    public ResponseEntity<Void> alterarSenha(
            @PathVariable("id") String id,
            @RequestBody TrocarSenhaUsuarioDTO dto) {

        var erros = this.validator.validateObject(dto)
                .getAllErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toSet());

        if (!erros.isEmpty())
            throw new UsuarioCamposInvalidosException(erros);

        this.trocarSenhaUsuario.trocarSenha(new TrocarSenhaUsuarioCommand(dto.senha()), id);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(
        @PathVariable("id") String id){
        this.deletarUsuario.delete(id);
        return ResponseEntity.ok().build();
    }

}
