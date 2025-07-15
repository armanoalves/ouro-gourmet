package br.com.ourogourmet.infrastructure.persistence;

import br.com.ourogourmet.core.entities.Cardapio;
import br.com.ourogourmet.core.entities.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Getter
public class CardapioEntity {

    @Id
    private String id;
    private String nome;
    private String descricao;
    private Double preco;
    private Boolean consumoLocal;
    private String foto;
    private LocalDateTime dataAlteracao;
    private LocalDateTime dataCriacao;

    public CardapioEntity(Cardapio cardapio) {
        this.id = cardapio.getId();
        this.nome = cardapio.getNome();
        this.descricao = cardapio.getDescricao();
        this.preco = cardapio.getPreco();
        this.consumoLocal = cardapio.getConsumoLocal();
        this.foto = cardapio.getFoto();
    }

    public Cardapio toDomain() {
        var cardapio = Cardapio.create(this.getNome(),
                this.descricao,
                this.preco,
                this.consumoLocal,
                this.foto);
        cardapio.setId(this.id);
        return cardapio;
    }

    @PrePersist
    @PreUpdate
    void prePersist() {
        this.dataCriacao = LocalDateTime.now();
        this.dataAlteracao = LocalDateTime.now();
    }
}
