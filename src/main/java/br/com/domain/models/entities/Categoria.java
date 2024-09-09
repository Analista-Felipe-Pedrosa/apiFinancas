package br.com.domain.models.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "categoria")
@Data
public class Categoria {
	
	@Id	
	private UUID id;
	
	@Column(name = "nome", length = 50, nullable = false)
	private String nome;
	
	@ManyToMany(mappedBy = "categorias")
	private List<Conta> contas;
}
