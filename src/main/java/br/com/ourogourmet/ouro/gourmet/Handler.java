package br.com.ourogourmet.ouro.gourmet;

public interface Handler {

    void setNext(BaseRule baseRule);
    void handle(Object object);
}
