package br.com.ourogourmet.application.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AlterarRestauranteDTO(
        @NotBlank(message = "O campo nome é obrigatório.") String nome,
        @NotBlank(message = "O campo tipoCozinha é obrigatório.") String tipoCozinha,
        @NotBlank(message = "O campo horaFuncionamentoDe é obrigatório.") @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "Formato de hora inválido (HH:mm)") String horaFuncionamentoDe,
        @NotBlank(message = "O campo horarioFuncionamentoAte é obrigatório.") @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "Formato de hora inválido (HH:mm)") String horarioFuncionamentoAte,
        @NotNull(message = "O campo usuario é obrigatório.") AlterarUsuarioRestauranteDTO usuario){}
