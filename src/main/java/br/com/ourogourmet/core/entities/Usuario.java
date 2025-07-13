package br.com.ourogourmet.core.entities;


import br.com.ourogourmet.core.dto.AlterarUsuarioDTO;
import br.com.ourogourmet.core.dto.CriarUsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private String id;
    private String nome;
    private String email;
    private String login;
    private Boolean ativo;
    private String senha;
    private LocalDate dataAlteracao;
    private String endereco;

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
