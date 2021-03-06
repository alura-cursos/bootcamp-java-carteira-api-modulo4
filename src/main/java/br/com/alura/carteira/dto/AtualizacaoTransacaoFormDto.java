package br.com.alura.carteira.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtualizacaoTransacaoFormDto extends TransacaoFormDto {

	@NotNull
	private Long id;

}
