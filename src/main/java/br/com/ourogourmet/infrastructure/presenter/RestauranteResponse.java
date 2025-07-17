package br.com.ourogourmet.infrastructure.presenter;

import lombok.AllArgsConstructor;

import java.time.LocalTime;

@AllArgsConstructor
public class RestauranteResponse {
    private Long id;
    private String nome;
    private String tipoCozinha;
    private LocalTime horarioFuncionamentoDe;
    private LocalTime horarioFuncionamentoAte;
    private UsuarioResponse usuario;
}
