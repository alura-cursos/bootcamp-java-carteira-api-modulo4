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
public class AtualizacaoUsuarioFormDto extends UsuarioFormDto {

	@NotNull
	private Long id;

	private String senha;

}
