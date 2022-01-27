package br.org.generation.blogPessoal.blogPessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.blogPessoal.blogPessoal.model.Postagem;


@Repository //responsável pela comunicação com o banco de dados usando métodos com  
public interface PostagemRepository extends JpaRepository <Postagem, Long> // herdando interface JpaRepository
{
	//equivalente ao SELECT*FROM tb_postagem where titulo like ||| IgnoreCase é para não diferenciar maiúsculas de minusculas.
	public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);

}
