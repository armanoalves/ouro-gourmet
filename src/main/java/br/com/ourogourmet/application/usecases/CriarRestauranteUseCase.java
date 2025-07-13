package br.com.ourogourmet.application.usecases;

import br.com.ourogourmet.core.entities.Usuario;

import java.time.LocalTime;

public interface CriarRestauranteUseCase {
    void execute(CriarRestauranteCommand restauranteCommand);

    record CriarRestauranteCommand(String nome, String tipoCozinha, LocalTime horarioFuncionamentoDe, LocalTime horarioFuncionamentoAte, Usuario usuario){

    }
}
