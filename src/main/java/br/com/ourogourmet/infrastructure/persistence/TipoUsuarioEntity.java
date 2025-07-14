package br.com.ourogourmet.infrastructure.persistence;

import br.com.ourogourmet.core.entities.TipoUsuario;
import br.com.ourogourmet.core.entities.enums.TipoUsuarioEnum;
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
    @Enumerated(EnumType.STRING)
    private TipoUsuarioEnum tipo;

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
