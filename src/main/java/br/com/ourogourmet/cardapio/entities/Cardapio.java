package br.com.ourogourmet.cardapio.entities;

import br.com.ourogourmet.cardapio.usecase.AlterarCardapioUseCase.AlterarCardapioDTO;
import br.com.ourogourmet.cardapio.usecase.CriarCardapioUseCase.CriarCardapioDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Getter
public class Cardapio {

    @Id
    private String id;
    private String nome;
    private String descricao;
    private Double preco;
    private Boolean consumoLocal;
    private String foto;
    private LocalDate dataAlteracao;
    private LocalDate dataCriacao;

    public Cardapio(AlterarCardapioDTO alterarCardapioDTO){
        this.nome = alterarCardapioDTO.nome();
        this.descricao = alterarCardapioDTO.descricao();
        this.preco = alterarCardapioDTO.preco();
        this.consumoLocal = alterarCardapioDTO.cosumoLocal();
        this.foto = alterarCardapioDTO.foto();
        this.dataAlteracao = LocalDate.now();
        this.dataCriacao = LocalDate.now();
    }

    public Cardapio(CriarCardapioDTO criarCardapioDTO){
        this.id = String.valueOf(UUID.randomUUID());
        this.nome = criarCardapioDTO.nome();
        this.descricao = criarCardapioDTO.descricao();
        this.preco = criarCardapioDTO.preco();
        this.consumoLocal = criarCardapioDTO.cosumoLocal();
        this.foto = criarCardapioDTO.foto();
        this.dataAlteracao = LocalDate.now();
        this.dataCriacao = LocalDate.now();
    }

    @PrePersist
    @PreUpdate
    void prePersist(){
        this.dataAlteracao = LocalDate.now();
        this.dataCriacao = LocalDate.now();
    }
}
