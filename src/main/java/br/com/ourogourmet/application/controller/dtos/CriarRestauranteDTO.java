package br.com.ourogourmet.application.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CriarRestauranteDTO(
        @NotBlank(message = "Nome é obrigatório.") String nome,
        @NotBlank(message = "Tipo Cozinha é obrigatório.") String tipoCozinha,
        @NotBlank(message = "Informe o horário de funcionamento.") @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "Formato de hora inválido (HH:mm)") String horaFuncionamentoDe,
        @NotBlank(message = "Informe o horário de funcionamento.") @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "Formato de hora inválido (HH:mm)") String horarioFuncionamentoAte) {
}
