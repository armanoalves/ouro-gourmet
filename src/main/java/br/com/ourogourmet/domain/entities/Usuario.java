package br.com.ourogourmet.domain.entities;


import br.com.ourogourmet.domain.usecases.AlterarUsuarioUseCase.AlterarUsuarioCommand;
import br.com.ourogourmet.domain.usecases.CriarUsuarioUseCase.CriarUsuarioCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Usuario {

    private String id;
    private String nome;
    private String email;
    private String login;
    private Boolean ativo;
    private String senha;
    private LocalDate dataAlteracao;
    private String endereco;

    protected Usuario(){}

    public static Usuario create(String nome,String email, String login, Boolean ativo, String senha, LocalDate dataAlteracao, String endereco){

        var usuario = new Usuario();

        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setLogin(login);
        usuario.setAtivo(ativo);
        usuario.setSenha(senha);
        usuario.setDataAlteracao(dataAlteracao);
        usuario.setEndereco(endereco);

        return usuario;
    }

    public static Usuario alterar(AlterarUsuarioCommand alterarUsuarioDTO){

        return create(alterarUsuarioDTO.nome(),
                alterarUsuarioDTO.email(),
                alterarUsuarioDTO.login(),
                alterarUsuarioDTO.ativo(),
                null,
                LocalDate.now(),
                alterarUsuarioDTO.endereco());
    }

    public static Usuario incluir(CriarUsuarioCommand criarUsuario){

        var usuario = create(criarUsuario.nome(),
                criarUsuario.email(),
                criarUsuario.login(),
                criarUsuario.ativo(),
                criarUsuario.senha(),
                LocalDate.now(),
                criarUsuario.endereco());

        usuario.setId(String.valueOf(UUID.randomUUID()));
        return usuario;
    }

    public void alterarSenha(String novaSenha) {
        this.setSenha(novaSenha);
    }


    private void setSenha(String senha) {
        this.senha = senha;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDataAlteracao(LocalDate dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
