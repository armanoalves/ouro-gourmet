package br.com.ourogourmet.application.usecases;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface CriarCardapioUseCase {

    void save(CriarCardapioCommand cardapio);

    record CriarCardapioCommand(
            String nome,
            String descricao,
            Double preco,
            String foto,
            Boolean cosumoLocal) {
    }

}
