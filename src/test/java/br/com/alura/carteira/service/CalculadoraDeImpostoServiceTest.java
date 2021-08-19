package br.com.alura.carteira.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.carteira.modelo.TipoTransacao;
import br.com.alura.carteira.modelo.Transacao;
import br.com.alura.carteira.modelo.Usuario;

class CalculadoraDeImpostoServiceTest {

	private CalculadoraDeImpostoService calculadora;
	
	@BeforeEach
	void before() {
		this.calculadora = new CalculadoraDeImpostoService();
	}
	
	private Transacao getTransacao(TipoTransacao tipo, BigDecimal preco) {
		return new Transacao(
				333l, 
				"ITSA4", 
				LocalDate.now(), 
				preco, 
				300, 
				tipo, 
				new Usuario(12l, "Rafaela", "rafa", "123456"));
	}

	@Test
	public void transacaoDoTipoCompraNaoDeveriaTerImposto() {
		Transacao compra = getTransacao(TipoTransacao.COMPRA, new BigDecimal("10"));
		
		BigDecimal imposto = calculadora.calcular(compra);
		
		assertEquals(BigDecimal.ZERO, imposto);
	}

	@Test
	public void transacaoDeVendaComValorMenorDoQueVinteMilNaoDeveriaTerImposto() {
		Transacao venda = getTransacao(TipoTransacao.VENDA, new BigDecimal("10"));
		
		BigDecimal imposto = calculadora.calcular(venda);
		
		assertEquals(BigDecimal.ZERO, imposto);
	}

	@Test
	public void transacaoDeVendaComValorMaiorDoQueVinteMilDeveriaTerImpostoDeQuinzePorCdento() {
		Transacao venda = getTransacao(TipoTransacao.VENDA, new BigDecimal("90"));
		
		BigDecimal imposto = calculadora.calcular(venda);
		
		assertEquals(new BigDecimal("1050.00"), imposto);
	}

}
