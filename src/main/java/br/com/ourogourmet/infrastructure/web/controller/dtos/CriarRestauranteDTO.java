package br.com.ourogourmet.infrastructure.web.controller.dtos;

import jakarta.validation.constraints.Pattern;

public record CriarRestauranteDTO(String nome,
  String tipoCozinha,
  @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "Formato de hora inválido (HH:mm)") String horaFuncionamentoDe,
  @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "Formato de hora inválido (HH:mm)") String horarioFuncionamentoAte) {
}
