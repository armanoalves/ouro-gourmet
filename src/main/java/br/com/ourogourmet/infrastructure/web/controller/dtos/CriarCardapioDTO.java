package br.com.ourogourmet.infrastructure.web.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarCardapioDTO(
        @NotBlank(message = "O campo nome é obrigatório.")
        String nome,

        @NotBlank(message = "O campo descricao é obrigatório.")
        String descricao,

        @NotNull(message = "O campo preco é obrigatório.")
        Double preco,

        String foto,

        @NotNull
        Boolean cosumoLocal)  {
}