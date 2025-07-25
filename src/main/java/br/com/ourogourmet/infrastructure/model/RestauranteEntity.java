package br.com.ourogourmet.infrastructure.model;

import br.com.ourogourmet.domain.entities.Restaurante;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

import static java.util.Objects.nonNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name="restaurante")
public class RestauranteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String tipoCozinha;
    private LocalTime horarioFuncionamentoDe;
    private LocalTime horarioFuncionamentoAte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    public RestauranteEntity(){}

    public RestauranteEntity(Restaurante restaurante){
        this.id = nonNull(restaurante.getId()) ? this.getId() : null;
        this.nome = restaurante.getNome();
        this.tipoCozinha = restaurante.getTipoCozinha();
        this.horarioFuncionamentoDe = restaurante.getHorarioFuncionamentoDe();
        this.horarioFuncionamentoAte = restaurante.getHorarioFuncionamentoAte();
        this.usuario = nonNull(restaurante.getUsuario()) ?  new UsuarioEntity(restaurante.getUsuario()) : null;
    }

    public Restaurante toDomain(){
        var restaurante = Restaurante.create(this.nome,
                this.tipoCozinha,
                this.horarioFuncionamentoDe,
                this.horarioFuncionamentoAte,
                nonNull(this.usuario) ? this.usuario.toDomain() : null);
        restaurante.setId(this.id);
        return restaurante;
    }
 }
