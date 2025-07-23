package br.com.ourogourmet.domain.entities;

import br.com.ourogourmet.domain.usecases.AlterarCardapioUseCase.AlterarCardapioComand;
import br.com.ourogourmet.domain.usecases.CriarCardapioUseCase.CriarCardapioCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Cardapio {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private Boolean consumoLocal;
    private String foto;

    protected Cardapio() {
    }

    public static Cardapio create(String nome,
                                  String descricao,
                                  Double preco,
                                  Boolean consumoLocal,
                                  String foto) {

        var cardapio = new Cardapio();

        cardapio.setNome(nome);
        cardapio.setDescricao(descricao);
        cardapio.setPreco(preco);
        cardapio.setFoto(foto);
        cardapio.setConsumoLocal(consumoLocal);
        return cardapio;
    }

    public static Cardapio alterar(AlterarCardapioComand alterarCardapioDTO) {

        return create(alterarCardapioDTO.nome(),
                alterarCardapioDTO.descricao(),
                alterarCardapioDTO.preco(),
                alterarCardapioDTO.cosumoLocal(),
                alterarCardapioDTO.foto()
        );
    }

    public static Cardapio incluir(CriarCardapioCommand criarCardapioDTO) {

        return create(criarCardapioDTO.nome(),
                criarCardapioDTO.descricao(),
                criarCardapioDTO.preco(),
                criarCardapioDTO.cosumoLocal(),
                criarCardapioDTO.foto());
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void setConsumoLocal(Boolean consumoLocal) {
        this.consumoLocal = consumoLocal;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

}
