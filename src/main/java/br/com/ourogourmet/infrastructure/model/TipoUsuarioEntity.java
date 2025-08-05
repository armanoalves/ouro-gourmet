package br.com.ourogourmet.infrastructure.model;

import br.com.ourogourmet.domain.entities.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Getter
@AllArgsConstructor
@Table(name="tipo_usuario")
public class TipoUsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    private String tipo;

    protected TipoUsuarioEntity(){}

    public TipoUsuarioEntity(TipoUsuario tipoUsuario){
        this.tipo = tipoUsuario.getTipo();
    }

    public TipoUsuario toDomain(){
        var tipoUsuario = TipoUsuario.create(this.tipo);
        tipoUsuario.setId(this.id);
        return tipoUsuario;
    }
}
