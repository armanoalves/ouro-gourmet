package br.com.ourogourmet.application.controller;

import br.com.ourogourmet.domain.usecases.AlterarRestauranteUseCase;
import br.com.ourogourmet.domain.usecases.AlterarRestauranteUseCase.AlterarRestauranteCommand;
import br.com.ourogourmet.domain.usecases.CriarRestauranteUseCase;
import br.com.ourogourmet.domain.usecases.CriarRestauranteUseCase.CriarRestauranteCommand;
import br.com.ourogourmet.domain.exceptions.RestauranteCamposInvalidosException;
import br.com.ourogourmet.application.controller.dtos.AlterarRestauranteDTO;
import br.com.ourogourmet.application.controller.dtos.CriarRestauranteDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.stream.Collectors;

import static org.springframework.web.util.UriComponentsBuilder.fromPath;

@RestController
@RequestMapping(path=RestauranteController.PATH)
@Tag(name="Restaurantes", description = "Cadastro de restaurantes")
@AllArgsConstructor
public class RestauranteController {

    private final CriarRestauranteUseCase criarRestauranteUseCase;
    private final AlterarRestauranteUseCase alterarRestauranteUseCase;

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
        var id = criarRestauranteUseCase.execute(cmd);

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

        this.alterarRestauranteUseCase.execute(cmd);

        var status = HttpStatus.NO_CONTENT;

        return ResponseEntity.status(status.value()).build();
    }
}
