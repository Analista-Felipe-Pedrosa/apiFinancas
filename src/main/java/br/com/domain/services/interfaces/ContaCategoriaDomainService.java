package br.com.domain.services.interfaces;

import br.com.domain.models.dtos.ContaCategoriaRequestDto;
import br.com.domain.models.dtos.ContaCategoriaResponseDto;

public interface ContaCategoriaDomainService {

	public ContaCategoriaResponseDto inserir(ContaCategoriaRequestDto request) throws Exception;
}
