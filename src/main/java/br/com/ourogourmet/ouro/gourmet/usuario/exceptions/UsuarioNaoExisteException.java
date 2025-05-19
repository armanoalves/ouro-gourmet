package br.com.ourogourmet.ouro.gourmet.usuario.exceptions;


import java.util.Set;
import java.util.stream.Collectors;

public class UsuarioNaoExisteException extends RuntimeException {

    public UsuarioNaoExisteException(String id){
        super("Usuário não encontrado. ID:".concat(id));
    }

    public UsuarioNaoExisteException(Set<String> violations){
        super(violations.stream().collect(Collectors.joining(" - ")));
    }
}
