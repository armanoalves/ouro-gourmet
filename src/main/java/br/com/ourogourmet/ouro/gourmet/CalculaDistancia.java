package br.com.ourogourmet.ouro.gourmet;

public class CalculaDistancia extends BaseRule{

    @Override
    public void handle(Object object) {
        if (nextRule!=null)
            nextRule.handle(object);
    }

    private CalculaDistancia() {
    }

    public static CalculaDistancia createCalculaDistancia() {
        return new CalculaDistancia();
    }
}
