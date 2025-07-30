package br.com.ourogourmet.infrastructure.model;

import br.com.ourogourmet.domain.entities.TipoUsuario;
import br.com.ourogourmet.domain.entities.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="usuario")
@AllArgsConstructor
public class UsuarioEntity {

    @Id
    private String id;
    private String nome;
    private String email;
    private String login;
    private Boolean ativo;
    private String senha;
    private LocalDate dataAlteracao;
    private String endereco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_usuario_id", nullable = false)
    private TipoUsuarioEntity tipoUsuario;

    protected UsuarioEntity(){}

    public UsuarioEntity(Usuario usuario){
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.login = usuario.getLogin();
        this.ativo = usuario.getAtivo();
        this.senha = usuario.getSenha();
        this.dataAlteracao = usuario.getDataAlteracao();
        this.endereco = usuario.getEndereco();
    }

    public Usuario toDomain(){
        var usuario = Usuario.create(this.getNome(),
                this.email,
                this.login,
                this.ativo,
                this.senha,
                this.dataAlteracao,
                this.endereco);
        usuario.setId(this.id);
        return usuario;
    }

    @PreUpdate
    void prePersist() {
        this.dataAlteracao = LocalDate.now();
    }
}
