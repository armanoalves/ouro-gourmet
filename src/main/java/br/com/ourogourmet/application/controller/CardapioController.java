package br.com.ourogourmet.application.controller;

import br.com.ourogourmet.application.controller.dtos.AlterarCardapioDTO;
import br.com.ourogourmet.application.controller.dtos.CriarCardapioDTO;
import br.com.ourogourmet.domain.entities.Cardapio;
import br.com.ourogourmet.domain.exceptions.CardapioCamposInvalidosException;
import br.com.ourogourmet.domain.usecases.*;
import br.com.ourogourmet.domain.usecases.AlterarCardapioUseCase.AlterarCardapioComand;
import br.com.ourogourmet.domain.usecases.CriarCardapioUseCase.CriarCardapioCommand;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/cardapio")
@Tag(name = "Cardapio", description = "Cadastro de cardapio")
public class CardapioController {

    private static final Logger logger = LoggerFactory.getLogger(CardapioController.class);

    private final CriarCardapioUseCase criarCardapio;
    private final AlterarCardapioUseCase alterarCardapio;
    private final DeletarCardapioUseCase deletarCardapio;
    private final GetAllCardapioUseCase getAllCardapios;
    private final GetByIdCardapioUseCase getByIdCardapio;
    private final Validator validator;

    public CardapioController(CriarCardapioUseCase criarCardapio,
                              AlterarCardapioUseCase alterarCardapio,
                              DeletarCardapioUseCase deletarCardapio,
                              GetAllCardapioUseCase getAllCardapios,
                              GetByIdCardapioUseCase getByIdCardapio,
                              Validator validator) {
        this.criarCardapio = criarCardapio;
        this.alterarCardapio = alterarCardapio;
        this.deletarCardapio = deletarCardapio;
        this.getAllCardapios = getAllCardapios;
        this.getByIdCardapio = getByIdCardapio;
        this.validator = validator;
    }

    @GetMapping
    public ResponseEntity<List<Cardapio>> findAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        var cardapio = this.getAllCardapios.findAll(page, size);
        return ResponseEntity.ok(cardapio);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cardapio> findById(
            @PathVariable("id") String id) {
        var cardapio = this.getGetByIdCardapio().findById(id);
        return ResponseEntity.ok(cardapio);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CriarCardapioDTO cardapioDTO) {
        var erros = this.validator.validateObject(cardapioDTO)
                .getAllErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toSet());
        if (!erros.isEmpty())
            throw new CardapioCamposInvalidosException(erros);

        var cmd = new CriarCardapioCommand(
                cardapioDTO.nome(),
                cardapioDTO.descricao(),
                cardapioDTO.preco(),
                cardapioDTO.foto(),
                cardapioDTO.cosumoLocal());
         Cardapio.incluir(cmd);

        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable("id") String id,
            @RequestBody AlterarCardapioDTO cardapioDTO) {

        var erros = this.validator.validateObject(cardapioDTO)
                .getAllErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toSet());

        if (!erros.isEmpty())
            throw new CardapioCamposInvalidosException(erros);

        this.alterarCardapio.update(new AlterarCardapioComand(cardapioDTO.nome(),
                cardapioDTO.descricao(),
                cardapioDTO.preco(),
                cardapioDTO.foto(),
                cardapioDTO.cosumoLocal()), id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") String id) {
        this.deletarCardapio.delete(id);
        return ResponseEntity.ok().build();
    }

}
