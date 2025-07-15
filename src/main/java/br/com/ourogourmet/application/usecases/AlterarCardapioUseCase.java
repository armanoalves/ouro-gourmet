package br.com.ourogourmet.application.usecases;


import java.time.LocalDateTime;

public interface AlterarCardapioUseCase {
    void update(AlterarCardapioComand cardapio, String id);

    record AlterarCardapioComand(
            String nome,
            String descricao,
            Double preco,
            String foto,
            Boolean cosumoLocal) {
    }
}


