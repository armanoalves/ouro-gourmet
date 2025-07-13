package br.com.ourogourmet.core.entities;

import java.time.LocalTime;

public class Restaurante {

    private String id;
    private String nome;
    private String tipoCozinha;
    private LocalTime horarioFuncionamentoDe;
    private LocalTime horarioFuncionamentoAte;
    private Usuario usuario;

    public static Restaurante create(String nome, String tipoCozinha, LocalTime horarioFuncionamentoDe, LocalTime horarioFuncionamentoAte, Usuario usuario){

        if (nome == null)
            throw new IllegalArgumentException("Faltam dados obrigatórios!");

        Restaurante restaurante = new Restaurante();

        restaurante.setNome(nome);
        restaurante.setHorarioFuncionamento(horarioFuncionamentoDe,horarioFuncionamentoAte);
        restaurante.setTipoCozinha(tipoCozinha);
        restaurante.setUsuario(usuario);
        return restaurante;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setHorarioFuncionamento(LocalTime horarioFuncionamentoDe, LocalTime horarioFuncionamentoAte) {
        if (horarioFuncionamentoDe == null || horarioFuncionamentoAte == null)
            throw new IllegalArgumentException("Faltam dados obrigatórios!");

        this.horarioFuncionamentoDe = horarioFuncionamentoDe;
        this.horarioFuncionamentoAte = horarioFuncionamentoAte;
    }

    public void setTipoCozinha(String tipoCozinha) {
        this.tipoCozinha = tipoCozinha;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNome(){
        return this.nome;
    }

}
