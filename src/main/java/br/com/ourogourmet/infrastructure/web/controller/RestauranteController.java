package br.com.ourogourmet.infrastructure.web.controller;

import br.com.ourogourmet.application.usecases.CriarRestauranteUseCase;
import br.com.ourogourmet.application.usecases.CriarRestauranteUseCase.CriarRestauranteCommand;
import br.com.ourogourmet.infrastructure.web.controller.dtos.CriarRestauranteDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

import static org.springframework.web.util.UriComponentsBuilder.fromPath;

@RestController
@RequestMapping(path=RestauranteController.PATH)
@Tag(name="Restaurantes", description = "Cadastro de restaurantes")
@AllArgsConstructor
public class RestauranteController {

    private final CriarRestauranteUseCase criarRestauranteUseCase;
    public static final String PATH = "/restaurantes";

    @PostMapping
    public ResponseEntity<Long> criarRestaurante(
            @RequestBody CriarRestauranteDTO restauranteDTO){

        var cmd = new CriarRestauranteCommand(restauranteDTO.nome(),
                restauranteDTO.tipoCozinha(),
                LocalTime.parse(restauranteDTO.horaFuncionamentoDe()),
                LocalTime.parse(restauranteDTO.horarioFuncionamentoAte()));
        var id = criarRestauranteUseCase.execute(cmd);

        ResponseEntity.created(fromPath(RestauranteController.PATH + "/{id}").build(id)).build();
        return ResponseEntity.ok().build();
    }
}
