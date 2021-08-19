package br.com.alura.carteira.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.modelo.TipoTransacao;
import br.com.alura.carteira.modelo.Usuario;
import br.com.alura.carteira.repository.TransacaoRepository;
import br.com.alura.carteira.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {
	
	@Mock
	private TransacaoRepository transacaoRepository;

	@Mock
	private UsuarioRepository usuarioRepository;

	@InjectMocks
	private TransacaoService service;

	@Test
	void deveriaCadastrarUmaTransacao() {
		TransacaoFormDto form = new TransacaoFormDto(
				"ITSA4",
				new BigDecimal("11.87"),
				LocalDate.now(),
				20,
				TipoTransacao.COMPRA,
				12l);

		Mockito
		.when(usuarioRepository.getById(form.getUsuarioId()))
		.thenReturn(new Usuario(form.getUsuarioId(), "Rafaela", "rafa", "123456"));

		TransacaoDto dto = service.cadastrar(form);

		assertEquals(form.getTicker(), dto.getTicker());
		assertEquals(form.getPreco(), dto.getPreco());
		assertEquals(form.getQuantidade(), dto.getQuantidade());
		assertEquals(form.getTipo(), dto.getTipo());
	}

	@Test
	void naoDeveriaCadastrarUmaTransacaoComUsuarioInexistente() {
		TransacaoFormDto form = new TransacaoFormDto(
				"ITSA4",
				new BigDecimal("11.87"),
				LocalDate.now(),
				20,
				TipoTransacao.COMPRA,
				1l);

		assertThrows(IllegalArgumentException.class, () -> service.cadastrar(form));
	}

}
