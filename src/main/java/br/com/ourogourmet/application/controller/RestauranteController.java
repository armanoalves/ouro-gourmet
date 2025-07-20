package br.com.ourogourmet.application.controller;

import br.com.ourogourmet.application.controller.dtos.AlterarRestauranteDTO;
import br.com.ourogourmet.application.controller.dtos.CriarRestauranteDTO;
import br.com.ourogourmet.domain.entities.Restaurante;
import br.com.ourogourmet.domain.exceptions.RestauranteCamposInvalidosException;
import br.com.ourogourmet.domain.usecases.*;
import br.com.ourogourmet.domain.usecases.AlterarRestauranteUseCase.AlterarRestauranteCommand;
import br.com.ourogourmet.domain.usecases.CriarRestauranteUseCase.CriarRestauranteCommand;
import br.com.ourogourmet.infrastructure.adapter.repository.RestauranteEntityRepository;
import br.com.ourogourmet.infrastructure.repository.RestauranteRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.util.UriComponentsBuilder.fromPath;

@RestController
@RequestMapping(path=RestauranteController.PATH)
@Tag(name="Restaurantes", description = "Cadastro de restaurantes")
@AllArgsConstructor
public class RestauranteController {

    private final CriarRestauranteUseCase criarRestauranteUseCase;
    private final AlterarRestauranteUseCase alterarRestauranteUseCase;
    private final DeletarRestauranteUseCase deletarRestauranteUseCase;
    private final GetByIdRestauranteUseCase getByIdRestauranteUseCase;
    private final GetAllRestauranteUseCase getAllRestaurantesUseCase;
    private final RestauranteRepository restauranteRepository;

    private final Validator validator;

    public static final String PATH = "/restaurantes";

    @PostMapping
    public ResponseEntity<Long> criarRestaurante(
            @RequestBody CriarRestauranteDTO dto){

        var erros = this.validator.validateObject(dto)
                .getAllErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toSet());

        if (!erros.isEmpty())
            throw new RestauranteCamposInvalidosException(erros);


        var cmd = new CriarRestauranteCommand(dto.nome(),
                dto.tipoCozinha(),
                LocalTime.parse(dto.horaFuncionamentoDe()),
                LocalTime.parse(dto.horarioFuncionamentoAte()));
        var id = criarRestauranteUseCase.execute(cmd, new RestauranteEntityRepository(restauranteRepository));

        ResponseEntity.created(fromPath(RestauranteController.PATH + "/{id}").build(id)).build();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> alterarRestaurante(
            @PathVariable("id") Long id,
            @RequestBody AlterarRestauranteDTO dto){

        var erros = this.validator.validateObject(dto)
                .getAllErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toSet());

        if (!erros.isEmpty())
            throw new RestauranteCamposInvalidosException(erros);

        var cmd = new AlterarRestauranteCommand(id,
                dto.nome(),
                dto.tipoCozinha(),
                LocalTime.parse(dto.horaFuncionamentoDe()),
                LocalTime.parse(dto.horarioFuncionamentoAte()),
                "");

        this.alterarRestauranteUseCase.execute(cmd,new RestauranteEntityRepository(restauranteRepository));

        var status = HttpStatus.NO_CONTENT;

        return ResponseEntity.status(status.value()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRestaurante(
            @PathVariable("id") Long id){

        this.deletarRestauranteUseCase.execute(id,new RestauranteEntityRepository(restauranteRepository));

        var status = HttpStatus.NO_CONTENT;

        return ResponseEntity.status(status.value()).build();
    }

    @GetMapping
    public ResponseEntity<List<Restaurante>> buscarTodosRestaurantes(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size){

        var restaurantes = this.getAllRestaurantesUseCase.execute(page, size, new RestauranteEntityRepository(restauranteRepository));

        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscarRestaurantePorId(
            @PathVariable("id") Long id){
        var restaurante = this.getByIdRestauranteUseCase.execute(id, new RestauranteEntityRepository(restauranteRepository));
        return ResponseEntity.ok(restaurante);
    }

}
