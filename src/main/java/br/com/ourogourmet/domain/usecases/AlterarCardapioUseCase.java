package br.com.ourogourmet.domain.usecases;


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


