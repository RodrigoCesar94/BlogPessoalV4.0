package br.org.generation.blogPessoal.blogPessoal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.blogPessoal.blogPessoal.model.Usuario;



@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	public Optional<Usuario> findByUsuario(String usuario); //Refere-se ao atributo usuario da model Usuario.
	
	 public List <Usuario> findAllByNomeContainingIgnoreCase(String nome); 

}
