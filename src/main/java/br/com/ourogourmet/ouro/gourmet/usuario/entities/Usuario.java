package br.com.ourogourmet.ouro.gourmet.usuario.entities;

import br.com.ourogourmet.ouro.gourmet.usuario.usecase.AlterarUsuarioUseCase.AlterarUsuarioDTO;
import br.com.ourogourmet.ouro.gourmet.usuario.usecase.CriarUsuarioUseCase.CriarUsuarioDTO;
import br.com.ourogourmet.ouro.gourmet.usuario.usecase.TrocarSenhaUsuarioUseCase.TrocarSenhaUsuarioDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Getter
public class Usuario {

    @Id
    String id;
    String nome;
    String email;
    String login;
    Boolean ativo;
    String senha;
    LocalDate dataAlteracao;
    String endereco;

    public Usuario(AlterarUsuarioDTO alterarUsuarioDTO){
        this.email = alterarUsuarioDTO.email();
        this.login = alterarUsuarioDTO.login();
        this.ativo = alterarUsuarioDTO.ativo();
        this.dataAlteracao = LocalDate.now();
        this.nome = alterarUsuarioDTO.nome();
        this.endereco = alterarUsuarioDTO.endereco();
    }

    public Usuario(CriarUsuarioDTO criarUsuario){
        this.id = String.valueOf(UUID.randomUUID());
        this.email = criarUsuario.email();
        this.senha = criarUsuario.senha();
        this.login = criarUsuario.login();
        this.ativo = criarUsuario.ativo();
        this.dataAlteracao = LocalDate.now();
        this.nome = criarUsuario.nome();
        this.endereco = criarUsuario.endereco();
    }

    public void alterarSenha(String novaSenha) {
        this.senha = novaSenha;
    }

}
