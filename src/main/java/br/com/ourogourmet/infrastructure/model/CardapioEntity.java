package br.com.ourogourmet.infrastructure.model;

import br.com.ourogourmet.domain.entities.Cardapio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Table(name="cardapio")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
public class CardapioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
