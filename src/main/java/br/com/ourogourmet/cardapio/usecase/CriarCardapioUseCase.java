package br.com.ourogourmet.cardapio.usecase;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface CriarCardapioUseCase {

        void save(CriarCardapioDTO cardapio);

        record CriarCardapioDTO(
                @NotBlank(message = "O campo nome é obrigatório.")
                String nome,

                @NotBlank(message = "O campo descrição é obrigatório.")
                String descricao,

                @NotBlank(message = "O campo preço é obrigatório.")
                Double preco,

                String foto,

                @NotNull
                Boolean cosumoLocal) {
        }

}
