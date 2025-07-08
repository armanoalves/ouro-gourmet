package br.com.ourogourmet.ouro.gourmet;

public class CalculaFrete extends BaseRule{

    @Override
    public void handle(Object object) {
        if (nextRule!=null)
            nextRule.handle(object);
    }

}
