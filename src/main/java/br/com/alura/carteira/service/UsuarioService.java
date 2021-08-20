package br.com.alura.carteira.service;

import java.util.Random;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.carteira.dto.AtualizacaoUsuarioFormDto;
import br.com.alura.carteira.dto.UsuarioDto;
import br.com.alura.carteira.dto.UsuarioFormDto;
import br.com.alura.carteira.modelo.Usuario;
import br.com.alura.carteira.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	private ModelMapper modelMapper = new ModelMapper();

	public Page<UsuarioDto> listar(Pageable paginacao) {
		return repository.findAll(paginacao).map(t -> modelMapper.map(t, UsuarioDto.class));
	}

	@Transactional
	public UsuarioDto cadastrar(UsuarioFormDto dto) {
		Usuario usuario = modelMapper.map(dto, Usuario.class);

		String senha = new Random().nextInt(999999) + "";
		usuario.setSenha(senha);

		repository.save(usuario);
		return modelMapper.map(usuario, UsuarioDto.class);
	}

	@Transactional
	public UsuarioDto atualizar(AtualizacaoUsuarioFormDto dto) {
		Long idUsuario = dto.getId();
		Usuario usuario = repository.findById(idUsuario).orElseThrow(() -> new EntityNotFoundException());
		
		usuario.atualizarInformacoes(dto.getNome(), dto.getLogin(), dto.getSenha());
		return modelMapper.map(usuario, UsuarioDto.class);
	}

	@Transactional
	public void remover(Long id) {
		repository.deleteById(id);
	}

	public UsuarioDto buscarPorId(Long id) {
		Usuario usuario = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		return modelMapper.map(usuario, UsuarioDto.class);
	}

}
