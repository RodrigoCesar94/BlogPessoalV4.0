package br.org.generation.blogPessoal.blogPessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.blogPessoal.blogPessoal.model.Tema;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long> // Estabelece que TemaRepository pode usar todos os
																	// métodos de JpaRepository
{

	public List<Tema> findAllByDescricaoContainingIgnoreCase(String descrição);

}
