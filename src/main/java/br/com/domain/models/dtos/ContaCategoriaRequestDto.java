package br.com.domain.models.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class ContaCategoriaRequestDto {

	private UUID contaId;
	private UUID categoriaId;	
}
