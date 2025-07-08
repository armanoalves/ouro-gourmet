package br.com.ourogourmet.ouro.gourmet;

public abstract class BaseRule implements Handler{

    protected BaseRule nextRule;

    @Override
    public void setNext(BaseRule baseRule) {
        this.nextRule = baseRule;
    }

    @Override
    public void handle(Object object) {
        if (nextRule!=null)
            nextRule.handle(object);
    }
}
