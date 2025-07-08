package br.com.ourogourmet.ouro.gourmet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static br.com.ourogourmet.ouro.gourmet.CalculaDistancia.createCalculaDistancia;

@SpringBootApplication
public class OuroGourmetApplication {

	public static void main(String[] args) {

		BaseRule regra1 = new CalculaPrazo();
		BaseRule regra2 = createCalculaDistancia();
		BaseRule regra3 = new CalculaFrete();

		regra1.setNext(regra2);
		regra2.setNext(regra3);

		regra1.handle(null);

		SpringApplication.run(OuroGourmetApplication.class, args);
	}

}
