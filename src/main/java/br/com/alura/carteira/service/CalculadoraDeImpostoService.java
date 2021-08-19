package br.com.alura.carteira.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.alura.carteira.modelo.TipoTransacao;
import br.com.alura.carteira.modelo.Transacao;

public class CalculadoraDeImpostoService {
	
	// 15% de imposto sobre o valor da transacao de VENDA que ultrapassar o limite de R$20.000,00
	public BigDecimal calcular(Transacao transacao) {
		if (TipoTransacao.COMPRA == transacao.getTipo()) {
			return BigDecimal.ZERO;
		}
		
		BigDecimal limite = new BigDecimal("20000.00");
		BigDecimal valorTotal = transacao.getPreco().multiply(new BigDecimal(transacao.getQuantidade()));
		if (valorTotal.compareTo(limite) < 1) {
			return BigDecimal.ZERO;
		}
		
		BigDecimal aliquotaImposto = new BigDecimal("0.15");
		return valorTotal.subtract(limite).multiply(aliquotaImposto).setScale(2, RoundingMode.HALF_UP);
	}

}
