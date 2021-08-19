package br.com.alura.carteira.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.alura.carteira.dto.ItemCarteiraDto;
import br.com.alura.carteira.modelo.TipoTransacao;
import br.com.alura.carteira.modelo.Transacao;
import br.com.alura.carteira.modelo.Usuario;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class TransacaoRepositoryTest {

	@Autowired
	private TestEntityManager em;
	
	@Autowired
	private TransacaoRepository repository;

	@Test
	void deveriaRetornarRelatorioDeCarteiraDeInvestimentos() {
		Usuario rafaela = new Usuario("Rafaela", "rafa@email.com", "123456");
		em.persist(rafaela);
		
		Transacao t1 = new Transacao("ITSA4", LocalDate.of(2021, 1, 1), new BigDecimal("10.00"), 50, TipoTransacao.COMPRA, rafaela);
		Transacao t2 = new Transacao("BBSE3", LocalDate.of(2021, 2, 1), new BigDecimal("22.80"), 80, TipoTransacao.COMPRA, rafaela);
		Transacao t3 = new Transacao("EGIE3", LocalDate.of(2021, 3, 5), new BigDecimal("40.00"), 25, TipoTransacao.COMPRA, rafaela);
		Transacao t4 = new Transacao("ITSA4", LocalDate.of(2021, 5, 15), new BigDecimal("11.20"), 40, TipoTransacao.COMPRA, rafaela);
		Transacao t5 = new Transacao("SAPR4", LocalDate.of(2021, 6, 20), new BigDecimal("4.02"), 120, TipoTransacao.COMPRA, rafaela);
		em.persist(t1);
		em.persist(t2);
		em.persist(t3);
		em.persist(t4);
		em.persist(t5);
	
		List<ItemCarteiraDto> relatorio = repository.relatorioCarteiraDeInvestimentos();

		assertThat(relatorio)
		.hasSize(4)
		.extracting(ItemCarteiraDto::getTicker, ItemCarteiraDto::getQuantidade, ItemCarteiraDto::getPercentual)
		.containsExactlyInAnyOrder(
				tuple("ITSA4", 90l, 0.28571),
				tuple("BBSE3", 80l, 0.25397),
				tuple("EGIE3", 25l, 0.07937),
				tuple("SAPR4", 120l, 0.38095)
		);
	}

}
