package br.com.ourogourmet.application.presenter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsuarioResponse {
    private String id;
    private String nome;
    private String email;
    private String login;
    private Boolean ativo;
    private String endereco;
}
