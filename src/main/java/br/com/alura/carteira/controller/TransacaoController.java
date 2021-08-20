package br.com.alura.carteira.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sun.istack.NotNull;

import br.com.alura.carteira.dto.AtualizacaoTransacaoFormDto;
import br.com.alura.carteira.dto.DetalhesTransacaoDto;
import br.com.alura.carteira.dto.TransacaoDto;
import br.com.alura.carteira.dto.TransacaoFormDto;
import br.com.alura.carteira.service.TransacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/transacoes")
@Api(tags = "Transacoes")
public class TransacaoController {

	@Autowired
	private TransacaoService service;

	@GetMapping
	@ApiOperation("Listar transacoes")
	public Page<TransacaoDto> listar(@PageableDefault(size = 10) Pageable paginacao) {
		return service.listar(paginacao);
	}

	@PostMapping
	@ApiOperation("Cadastrar transacoes")
	public ResponseEntity<TransacaoDto> cadastrar(@RequestBody @Valid TransacaoFormDto dto, UriComponentsBuilder uriBuilder) {
		TransacaoDto cadastrada = service.cadastrar(dto);
		URI endereco = uriBuilder.path("/transacoes/{id}").buildAndExpand(cadastrada.getId()).toUri();
		
		return ResponseEntity.created(endereco).body(cadastrada);
	}

	@PutMapping
	@ApiOperation("Atualizar transacao")
	public ResponseEntity<TransacaoDto> atualizar(@RequestBody @Valid AtualizacaoTransacaoFormDto dto) {
		TransacaoDto atualizada = service.atualizar(dto);
		
		return ResponseEntity.ok(atualizada);
	}

	@DeleteMapping("/{id}")
	@ApiOperation("Remover transacao")
	public ResponseEntity<TransacaoDto> remover(@PathVariable @NotNull Long id) {
		service.remover(id);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	@ApiOperation("Detalhar transacao")
	public ResponseEntity<DetalhesTransacaoDto> detalhar(@PathVariable @NotNull Long id) {
		DetalhesTransacaoDto detalhada = service.buscarPorId(id);

		return ResponseEntity.ok(detalhada);
	}

}
