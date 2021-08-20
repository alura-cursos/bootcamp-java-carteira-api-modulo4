package br.com.alura.carteira.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalhesTransacaoDto extends TransacaoDto {

	private LocalDate data;
	private UsuarioDto usuario;

}
