package br.com.ourogourmet.integrado.infrastruture.repository;

import br.com.ourogourmet.domain.entities.Cardapio;
import br.com.ourogourmet.domain.exceptions.CardapioNaoEncontradoException;
import br.com.ourogourmet.domain.usecases.AlterarCardapioUseCase;
import br.com.ourogourmet.infrastructure.repository.CardapioGatewayImp;
import br.com.ourogourmet.infrastructure.repository.CardapioJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestDatabase
@Sql(scripts = "/sql/cardapio.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/clean_cardapio.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class CardapioGatewayImpTest {

    @Autowired
    private CardapioGatewayImp cardapioGatewayImp;

    @Autowired
    private CardapioJpaRepository cardapioJpaRepository;

    @Test
    void deveBuscarCardapioPorIdComSucesso() {
        Long id = 99L;
        var cardapio = cardapioGatewayImp.getCardapioById(id);

        assertThat(cardapio)
                .isInstanceOf(Cardapio.class)
                .isNotNull();
        assertThat(cardapio.getId()).isEqualTo(99L);
        assertThat(cardapio.getNome()).isEqualTo("X-BURGUER");
        assertThat(cardapio.getDescricao()).isEqualTo("carne com queijo e pão");
        assertThat(cardapio.getPreco()).isEqualTo(25);
        assertThat(cardapio.getConsumoLocal()).isTrue();
    }

    @Test
    void deveRetornarExcecaoQuandoNaoEncontrarCardapioPorId() {
        Long id = 9999L;
        assertThrows(CardapioNaoEncontradoException.class, () -> cardapioGatewayImp.getCardapioById(id));
    }

    @Test
    void deveBuscarTodosCardapios() {
        List<Cardapio> listaCardapios = cardapioGatewayImp.getAllCardapio(0, 1);
        assertThat(listaCardapios)
                .isNotEmpty()
                .hasSize(2);
    }

    @Test
    void deveCriarCardapioComSucesso() {

        Cardapio cardapio = Cardapio.create("X-BURGUER COMPLETO", "carne com queijo e pão e tudo completo", 45.0, true, "foto.jpg");
        Cardapio cardapioSalvo = cardapioGatewayImp.criarCardapio(cardapio);

        assertThat(cardapioSalvo)
                .isNotNull()
                .isInstanceOf(Cardapio.class);
        assertThat(cardapioSalvo.getId()).isNotNull();
        assertThat(cardapioSalvo.getNome()).isEqualTo("X-BURGUER COMPLETO");
        assertThat(cardapioSalvo.getDescricao()).isEqualTo("carne com queijo e pão e tudo completo");
        assertThat(cardapioSalvo.getPreco()).isEqualTo(45.0);
        assertThat(cardapioSalvo.getConsumoLocal()).isTrue();

    }

    @Test
    void deveAtualizarCardapioComSucesso() {
        Cardapio alterar = Cardapio.alterar(new AlterarCardapioUseCase.AlterarCardapioComand(1L, "X-BURGUER ATUALIZADO", "carne com queijo e pão atualizado", 30.0, "foto_atualizada.jpg",false));
        Cardapio cardapioAtualizado = cardapioGatewayImp.atualizarCardapio(alterar);
        assertThat(cardapioAtualizado)
                .isNotNull()
                .isInstanceOf(Cardapio.class);
        assertThat(cardapioAtualizado.getId()).isEqualTo(1L);
        assertThat(cardapioAtualizado.getNome()).isEqualTo("X-BURGUER ATUALIZADO");
        assertThat(cardapioAtualizado.getDescricao()).isEqualTo("carne com queijo e pão atualizado");
        assertThat(cardapioAtualizado.getPreco()).isEqualTo(30.0);
        assertThat(cardapioAtualizado.getConsumoLocal()).isFalse();

    }

    @Test
    void deveDeletarCardapioComSucesso() {
        Long id = 98L;
        cardapioGatewayImp.deletarCardapio(id);
        assertFalse(cardapioJpaRepository.existsById(id));
    }

}