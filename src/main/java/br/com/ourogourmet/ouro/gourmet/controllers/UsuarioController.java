package br.com.ourogourmet.ouro.gourmet.controllers;

import br.com.ourogourmet.ouro.gourmet.dto.AlterarUsuarioDTO;
import br.com.ourogourmet.ouro.gourmet.dto.CriarUsuarioDTO;
import br.com.ourogourmet.ouro.gourmet.entities.Usuario;
import br.com.ourogourmet.ouro.gourmet.services.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/usuarios")
@Tag(name="Usuarios", description = "Cadastro de usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAllUsuarios(
            @RequestParam("page") int page,
            @RequestParam("size") int size){
        logger.info("Foi acessado o endpoint usuarios /usuarios");
        var usuarios = this.usuarioService.findAll(page, size);

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> findUsuarioById(
            @PathVariable("id") String id){
        logger.info("/usuarios/".concat(id));

        var usuario = this.usuarioService.findById(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Void> saveUsuario(
            @RequestBody CriarUsuarioDTO usuario){
        logger.info("POST -> /usuarios");
        this.usuarioService.save(usuario);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUsuario(
            @PathVariable("id") String id,
            @RequestBody AlterarUsuarioDTO usuario){
        logger.info("PUT -> /usuarios".concat(id));
        this.usuarioService.update(usuario,id);
        var status = HttpStatus.NO_CONTENT;

        return ResponseEntity.status(status.value()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(
        @PathVariable("id") String id){
        logger.info("DELETE -> /usuarios".concat(id));
        this.usuarioService.delete(id);
        return ResponseEntity.ok().build();
    }

}
