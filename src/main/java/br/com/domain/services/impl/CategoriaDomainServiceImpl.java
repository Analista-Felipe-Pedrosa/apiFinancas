package br.com.domain.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.domain.models.dtos.CategoriaRequestDto;
import br.com.domain.models.dtos.CategoriaResponseDto;
import br.com.domain.models.entities.Categoria;
import br.com.domain.services.interfaces.CategoriaDomainService;
import br.com.infrastructure.repositories.CategoriaRepository;

@Service
public class CategoriaDomainServiceImpl implements CategoriaDomainService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoriaResponseDto inserir(CategoriaRequestDto request) throws Exception {
		
		Categoria categoria = modelMapper.map(request, Categoria.class);
		categoria.setId(UUID.randomUUID());
				
		categoriaRepository.save(categoria);
		
		CategoriaResponseDto response = modelMapper.map(categoria, CategoriaResponseDto.class);
		
		return response;
	}

	@Override
	public CategoriaResponseDto atualizar(UUID id, CategoriaRequestDto request) throws Exception {
		
		Categoria categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada. Verifique o Id informado. "));
		
		categoria.setNome(request.getNome());
		
		categoriaRepository.save(categoria);
		
		CategoriaResponseDto response = modelMapper.map(categoria, CategoriaResponseDto.class);
				
		return response;
	}

	@Override
	public CategoriaResponseDto excluir(UUID id) throws Exception {
		
		Categoria categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada. Verifique o Id informado. "));
		
		categoriaRepository.delete(categoria);
		
		CategoriaResponseDto response = modelMapper.map(categoria, CategoriaResponseDto.class);
				
		return response;
		
	}

	@Override
	public List<CategoriaResponseDto> consultar() throws Exception {
		List<Categoria> categorias = categoriaRepository.findAll();
		
		List<CategoriaResponseDto> response = new ArrayList<CategoriaResponseDto>();
		
		for (Categoria categoria : categorias) {
			response.add(modelMapper.map(categoria, CategoriaResponseDto.class));
		}
		return response;
	}

	@Override
	public CategoriaResponseDto obterPorId(UUID id) throws Exception {
		
		Categoria categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada. Verifique o Id informado. "));
		
		CategoriaResponseDto response = modelMapper.map(categoria, CategoriaResponseDto.class);
		
		return response;
	}
}
