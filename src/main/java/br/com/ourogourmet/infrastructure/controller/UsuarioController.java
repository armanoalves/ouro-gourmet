package br.com.ourogourmet.infrastructure.controller;

import br.com.ourogourmet.application.interfaces.*;
import br.com.ourogourmet.core.dto.AlterarUsuarioDTO;
import br.com.ourogourmet.core.dto.CriarUsuarioDTO;
import br.com.ourogourmet.core.dto.TrocarSenhaUsuarioDTO;
import br.com.ourogourmet.core.dto.ValidarLoginUsuarioDTO;
import br.com.ourogourmet.core.entities.Usuario;
import br.com.ourogourmet.core.exceptions.UsuarioCamposInvalidosException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

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

        this.criarUsuario.save(usuario);
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

        this.validarLoginUsuario.validar(dto);

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

        this.alterarUsuario.update(usuario,id);

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

        this.trocarSenhaUsuario.trocarSenha(dto, id);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(
        @PathVariable("id") String id){
        this.deletarUsuario.delete(id);
        return ResponseEntity.ok().build();
    }

}
