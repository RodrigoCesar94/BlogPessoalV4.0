package br.org.generation.blogPessoal.blogPessoal.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_temas")
public class Tema {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}

	@NotBlank(message = "O atributo descrição não pode estar vazio")
	private String descricao;

	// criando uma lista do objeto <Postagem> puxando da model postagem
	@OneToMany(mappedBy = "tema") // referencia o tema como sendo o One dessa relação. Você deve se referenciar
	// ao One dessa relação na model de ONE. Aqui por exemplo é a model de ONE na
	// cardinalidade e portanto aqui é
	// que você deve fazer essa referenciação.
	@JsonIgnoreProperties("tema") // Aqui a notação está excluindo a possibilidade de haver um loop infinito onde
	// tema procura postagem e vice-versa. Significa que quandoi encontrar o tema,
	// ele deve parar.

	private List<Postagem> postagem;

}
