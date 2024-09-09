package br.com.domain.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.domain.models.dtos.CategoriaResponseDto;
import br.com.domain.models.dtos.ContaCategoriaRequestDto;
import br.com.domain.models.dtos.ContaCategoriaResponseDto;
import br.com.domain.models.dtos.ContaResponseDto;
import br.com.domain.models.entities.Categoria;
import br.com.domain.models.entities.Conta;
import br.com.domain.services.interfaces.ContaCategoriaDomainService;
import br.com.infrastructure.repositories.CategoriaRepository;
import br.com.infrastructure.repositories.ContaRepository;

@Service
public class ContaCategoriaDomainServiceImpl implements ContaCategoriaDomainService {

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ContaCategoriaResponseDto inserir(ContaCategoriaRequestDto request) throws Exception {

		Conta conta = contaRepository.findById(request.getContaId())
				.orElseThrow(() -> new IllegalArgumentException("Conta não encontrada, verifique o ID"));

		Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
				.orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada, verifique o ID"));

		// adicionando a categoria na conta
		conta.getCategorias().add(categoria);
		
		//salvando no banco de dados
		contaRepository.save(conta);

		// copiando os dados da conta e da categoria para o DTO de resposta
		ContaCategoriaResponseDto response = new ContaCategoriaResponseDto();
		response.setConta(modelMapper.map(conta, ContaResponseDto.class));
		response.setCategoria(modelMapper.map(categoria, CategoriaResponseDto.class));
				
		return response;		
	}

}
