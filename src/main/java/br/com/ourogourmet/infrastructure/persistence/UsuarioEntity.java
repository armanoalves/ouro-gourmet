package br.com.ourogourmet.infrastructure.persistence;

import br.com.ourogourmet.core.entities.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_usuario_id", nullable = false)
    private TipoUsuarioEntity tipoUsuario;
}
