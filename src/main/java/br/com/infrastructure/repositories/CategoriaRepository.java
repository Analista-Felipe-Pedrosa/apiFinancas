package br.com.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.domain.models.entities.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, UUID>{

	
}
