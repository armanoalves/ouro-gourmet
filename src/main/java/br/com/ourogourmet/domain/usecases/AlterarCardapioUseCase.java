package br.com.ourogourmet.domain.usecases;


public interface AlterarCardapioUseCase {
    void alterar(AlterarCardapioComand cardapio);

    record AlterarCardapioComand(
            Long id,
            String nome,
            String descricao,
            Double preco,
            String foto,
            Boolean cosumoLocal) {
    }
}


