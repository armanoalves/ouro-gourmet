package br.com.ourogourmet.application.presenter;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class RestauranteResponse {
    private Long id;
    private String nome;
    private String tipoCozinha;
    private LocalTime horarioFuncionamentoDe;
    private LocalTime horarioFuncionamentoAte;
    private UsuarioResponse usuario;
}
