package br.com.ourogourmet.domain.usecases;


public interface AlterarCardapioUseCase {
    void alterar(AlterarCardapioComand cardapio, Long id);

    record AlterarCardapioComand(
            String nome,
            String descricao,
            Double preco,
            String foto,
            Boolean cosumoLocal) {
    }
}


