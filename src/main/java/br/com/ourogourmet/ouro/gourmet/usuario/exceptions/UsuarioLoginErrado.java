package br.com.ourogourmet.ouro.gourmet.usuario.exceptions;

public class UsuarioLoginErrado extends RuntimeException {
    public UsuarioLoginErrado() {
        super("Usuario com login errado.");
    }
}
