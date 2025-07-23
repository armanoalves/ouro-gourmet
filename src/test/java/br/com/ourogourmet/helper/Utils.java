package br.com.ourogourmet.helper;

import br.com.ourogourmet.domain.entities.Cardapio;

public class Utils {

    public static Cardapio buildCardapio() {
        return Cardapio.create("teste", "Teste Cardapio", 10D, true, "");
    }
}
