package br.com.ourogourmet.domain.usecases;

public interface CriarCardapioUseCase {

    void criar(CriarCardapioCommand cardapio);

    record CriarCardapioCommand(
            String nome,
            String descricao,
            Double preco,
            String foto,
            Boolean cosumoLocal) {
    }

}
